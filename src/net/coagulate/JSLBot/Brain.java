package net.coagulate.JSLBot;

import net.coagulate.JSLBot.Handlers.Authorisation.Authorisation;
import net.coagulate.JSLBot.Handlers.Authorisation.DenyAll;
import net.coagulate.JSLBot.JSLBot.CmdHelp;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.logging.Logger;

import static java.util.logging.Level.*;

/**
 * Bot's brain, performs event handling etc.
 * This used to be a Set of Handler's in JSLBot its self but it comes with a lot of polluting Reflection code so it got moved here.
 * Note we get called by a lot of different threads, understanding "immediate" and "delayed" execution and the importance in terms of
 * both locking the bot from doing two things at once, as well as *NOT* locking important threads that have to deal with e.g. Circuit UDP traffic.
 * <p>
 * Getting these things wrong will likely result in lag, packet storms during catch up, events not arriving, failing synchronous-cross-thread executions and so on.
 *
 * @author Iain Price
 */
public class Brain {
	private final Logger log;
	@Nonnull
	private final Set<Handler> brain;
	@Nonnull
	private final JSLBot bot;
	private final Map<String,Set<Method>> handlermap=new HashMap<>();
	// brains have an internal queue for delayed processing events.
	private final List<Event> queue=new ArrayList<>();
	private final Map<String,Method> commandmap=new HashMap<>();
	private final Set<String> warned=new HashSet<>();
	// track our launch attempts, ALWAYS_RECONNECT will only permit 5 attempts in 10 minutes...
	private final Date[] launches=new Date[Constants.MAX_LAUNCH_ATTEMPTS];
	@Nonnull
	private Authorisation auth;
	private boolean procrastinate=true;

	Brain(@Nonnull final JSLBot bot) {
		auth=new DenyAll(bot);
		log=bot.getLogger("Brain");
		this.bot=bot;
		brain=new HashSet<>();
	}

	// ---------- INSTANCE ----------

	/**
	 * Get a method from a command name.
	 *
	 * @param name Command name (without suffix)
	 *
	 * @return Command's implementing method
	 */
	public Method getCommand(final String name) {
		return commandmap.get((name+"command").toLowerCase());
	}

	/**
	 * Get all commands.
	 *
	 * @return A set of the command names in the map.  Note these have the 'command' suffix.
	 */
	@Nonnull
	public Set<String> getCommands() {
		return new HashSet<>(commandmap.keySet());
	}

	// what passes for an API :P
	@Nullable
	public String execute(@Nonnull final Event event) { return execute(event,true); }

	public void queue(final Event event) {
		synchronized (queue) {
			queue.add(event);
			queue.notifyAll();
		}
	}

	/**
	 * Set the authorisation module.
	 *
	 * @param auth New module
	 */
	public void setAuth(@Nullable final Authorisation auth) {
		if (auth==null) { this.auth=new DenyAll(bot); }
		else { this.auth=auth; }
	}

	/**
	 * Check authorisation for an event.
	 *
	 * @param event Event to check
	 *
	 * @return null if approved, otherwise rejection reason
	 *
	 * @see Authorisation
	 */
	@Nullable
	public String auth(@Nonnull final CommandEvent event) {
		return auth.approve(event);
	}

	@Nonnull
	@Override
	public String toString() { return bot+"/Brain"; }

	// ----- Internal Instance -----
	boolean isEmpty() {return brain.isEmpty();}

	/**
	 * Set up the bot.
	 * Clear the event queue and cached handler map, repopulate the command map.
	 */
	void prepare() {
		queue.clear();
		handlermap.clear();
		commandmap.clear();
		populateCommandMap();
		procrastinate=true;
	}

	/**
	 * Load a set of handlers into the bot.
	 * Should be done before initialisation.
	 *
	 * @param handlers Array of handler names, full class name if outside net.coagulate.JSLBot.Handlers
	 */
	void loadHandlers(@Nonnull final String[] handlers) {
		for (final String handler: handlers) {
			loadHandler(handler);
		}
	}

	/**
	 * Process Delayed event queue.
	 * Called by, and only by, the BOT AI Thread.
	 * Sleeps, if procrastinating (not shutting down) for a defined delay.
	 * Pops an event off the queue if one is available and executes it as delayed mode execution (does not requeue event but runs in THIS thread)
	 */
	void think() {
		@Nullable Event event=null;
		synchronized (queue) {
			if (queue.isEmpty() && procrastinate) {
				Thread.currentThread().setName("Brain for "+bot.getUsername()+" procrastinating");
				try {
					queue.wait(Constants.BRAIN_PROCRASTINATES_FOR_MILLISECONDS);
				}
				catch (@Nonnull final InterruptedException ignored) {}
			}
			if (!queue.isEmpty()) { event=queue.remove(0); }
		}
		if (event!=null) {
			idle=false;
			execute(event,false);
			idle=true;
		}
		callMaintenance();
	}
	private boolean idle;
	/** Checks the bot isn't running a command, and has no queue */
	public boolean isIdle() {
		if (!idle) { return false; } // running a command
		return queue.isEmpty();
	}

	/**
	 * Stops the brain procrastinating waiting for events.
	 * Basically called during bot shutdown, we will stop the sleep-for-event behaviour, and wake up any sleeping threads.
	 * Any trapped threads, or new calls to think() will thus be released immediately (though perhaps after running an event...)
	 * <p>
	 * Make sure the bot really is quitting otherwise you'll drive the event queue thread into a CPU consuming tight loop.
	 */
	void stopProcrastinating() {
		procrastinate=false;
		synchronized (queue) { queue.notifyAll(); }
	}

	/**
	 * Tell handlers we are logged in.
	 * Some handlers will generate events such as "query balance" etc
	 */
	void loggedIn() {
		for (@Nonnull final Handler h: brain) {
			try { h.loggedIn(); }
			catch (@Nonnull final Exception e) {
				log.log(SEVERE,"Handler "+h+" exceptioned handling login",e);
			}
		}
	}

	/**
	 * Call on every login loop.
	 * Makes sure we don't get stuck in a rapidly spamming loop.  See Constants.java for the config.
	 */
	void loginLoopSafety() {
		// if we have any null slots then we didn't even launch MAX times yet
		for (int i=0;i<launches.length;i++) {
			if (launches[i]==null) {
				log.info("Reconnection Safety: We have not yet launched 5 times");
				launches[i]=new Date();
				return;// use slot, return OK
			}
		}
		// not not any null slots, whats the oldest timer?
		@Nullable Date oldest=null;
		for (@Nonnull final Date d: launches) {
			if (oldest==null) { oldest=d; }
			else { if (d.before(oldest)) { oldest=d; } }
		}
		if (oldest==null) {
			throw new AssertionError("How is oldest null at this point?  if null we should have hit 'launched less than 5 times'");
		}
		final long ago=new Date().getTime()-oldest.getTime();
		final int secondsago=(int) (ago/1000f);
		log.info("Reconnection Safety: Last 5 login attempts took place over "+secondsago+" seconds");
		if (ago<(Constants.MAX_LAUNCH_ATTEMPTS_WINDOW_SECONDS)) {
			log.severe("Reconnection Safety: This is less than the threshold of "+Constants.MAX_LAUNCH_ATTEMPTS_WINDOW_SECONDS+", tripping safety.");
			loginLoopSafetyViolation();
			return; // if we get here.
		}
		// otherwise, overwrite oldest date with now and continue
		for (int i=0;i<launches.length;i++) {
			if (launches[i]==oldest) {
				launches[i]=new Date();
				return;
			}
		}
		// should never get here
		throw new AssertionError("An oldest launch time was found in pass #1, but could not be found to be replaced in pass #2");
	}

	/**
	 * Get a handler by name.
	 *
	 * @param name Name of handler
	 *
	 * @return Handler if found, otherwise exception.
	 */
	@Nonnull
	public Handler getHandler(final String name) {
		for (@Nonnull final Handler h: brain) {
			if (h.getClass().getSimpleName().equals(name)) { return h; }
		}
		throw new IllegalArgumentException("No handler called '"+name+"' is loaded");
	}

	/**
	 * Attempt to load a handler into the brain.
	 *
	 * @param handlername Class name, can be abbreviated if in core Handlers package
	 */
	private void loadHandler(@Nullable final String handlername) {
		if (handlername==null) { throw new NullPointerException("No handler specified"); }
		try {
			@Nonnull final Handler h=createHandler(handlername);
			brain.add(h);
		}
		catch (@Nonnull final InvocationTargetException ex) {
			Throwable t=ex;
			if (ex.getCause()!=null) { t=ex.getCause(); }
			log.log(SEVERE, "Exception loading handler " + handlername, t);
		}
	}

	/**
	 * Scan all handlers in the brain for commands.
	 * A command is a method that is annotated with CmdHelp (and optionally its parameters with ParamHelp), and whose method name ends with "command".
	 */
	private void populateCommandMap() {
		for (@Nonnull final Handler h: brain) {
			for (@Nonnull final Method m: h.getClass().getMethods()) {
				if (m.getAnnotation(CmdHelp.class)!=null) {
					@Nonnull final String commandname=m.getName().toLowerCase();
					if (commandmap.containsKey(commandname)) {
						log.severe("Duplicate definition for command "+commandname);
					}
					else {
						if (commandname.endsWith("command")) {
							if (CommandEvent.class==m.getParameters()[0].getType()) {
								commandmap.put(commandname,m);
							}
							else {
								log.severe("Otherwise legitimate command "+commandname+" has incorrect first parameter - should be type CommandEvent");
							}
						}
						else {
							log.warning("Annotated command "+commandname+" does not have 'command' suffix and is inaccessible");
						}
					}
				}
			}
		}
	}

	/**
	 * Instantiate a handler.
	 *
	 * @param name Class name
	 *
	 * @return The handler
	 *
	 * @throws InvocationTargetException If the handler constructor throws an error.
	 */
	@Nonnull
	private Handler createHandler(@Nonnull final String name) throws InvocationTargetException {
		try {
			@Nonnull String classname=name;
			if (!name.contains(".")) { classname="net.coagulate.JSLBot.Handlers."+name; }
			@Nonnull final Class<?> c=Class.forName(classname);
			@Nonnull final Configuration subconfiguration=bot.config.subspace(name);
			@Nonnull final Constructor<?> cons=c.getConstructor(JSLBot.class,Configuration.class);
			return (Handler) (cons.newInstance(bot,subconfiguration));
		}
		catch (@Nonnull final SecurityException|NoSuchMethodException|ClassNotFoundException|IllegalAccessException|IllegalArgumentException|InstantiationException ex) {
			throw new AssertionError("Handler "+name+" fails to meet programming contract",ex);
		}
	}

	/**
	 * lowercase the first character of the event name.
	 * (this path may be redundant, if we case insensitive compare?)
	 *
	 * @param event Event to get the name of
	 *
	 * @return Event name with first character in lower case
	 */
	@Nonnull
	private String formatEventName(@Nonnull final Event event) {
		@Nonnull final String method=event.getName();
		@Nonnull final char[] c=method.toCharArray();
		c[0]=Character.toLowerCase(c[0]);
		return new String(c);
	}

	/**
	 * Runs the event in THIS thread.
	 * <p>
	 * This is called in both the immediate and delayed modes, from different places.
	 *
	 * @param event     Event to process
	 * @param immediate Is this the immediate mode run?  If set, we will queue XML/UDP events for the 2nd delayed pass.
	 *
	 * @return Response, if any, of the event, may be null.
	 */
	@Nullable
	private String execute(@Nonnull final Event event,
	                       final boolean immediate) {
		@Nonnull String fen=formatEventName(event);
		fen=fen+event.typeString();
		@Nonnull String method=fen;
		if (event instanceof UDPEvent || event instanceof XMLEvent) { method+=(immediate?"Immediate":"Delayed"); }
		@Nullable String response=null;
		if (immediate) { event.status(Event.STATUS.IMMEDIATE); }
		else { event.status(Event.STATUS.RUNNING); }
		if (!handlermap.containsKey(method)) { populateHandlerMap(event); }
		@Nullable Set<Method> handlers=null;
		if (event instanceof UDPEvent || event instanceof XMLEvent) { handlers=handlermap.get(method); }
		if (event instanceof CommandEvent) {
			handlers=new HashSet<>();
			final Method handler=commandmap.get(method.toLowerCase());
			if (handler==null) { return "Unknown Command:"+method.toLowerCase(); }
			handlers.add(handler);
		}
		if (Debug.TRACKCOMMANDS && event instanceof CommandEvent) {
			event.log(FINEST,"Entering executor in "+(immediate?"immediate":"delayed")+" mode");
		}
		if (handlers==null) {
			log.severe("Found a null map for "+method+", but this should have been populated");
			return "";
		}
		for (@Nonnull final Method handler: handlers) {
			try {
				@Nonnull final Object callon=findHandler(handler);
				if (event instanceof UDPEvent) { response=(String) handler.invoke(callon,event); }
				if (event instanceof XMLEvent) { response=(String) handler.invoke(callon,event); }
				if (event instanceof CommandEvent) {
					@Nonnull final CommandEvent cmd=(CommandEvent) event;
					response=cmd.run(callon,handler);
					cmd.response(response);
				}
			}
			catch (@Nonnull final IllegalAccessException ex) {
				log.warning("Method "+method+" has incorrect access modifier"); // impossible?
			}
			catch (@Nonnull final IllegalArgumentException ex) {
				log.warning("Method "+method+" has incorrect parameters"); // impossible?
			}
			catch (@Nonnull final InvocationTargetException ex) {
				Throwable t=ex;
				if (t.getCause()!=null) { t=t.getCause(); }
				log.log(SEVERE,"Method "+method+" threw an error:",t);
			}
		}

		if (event instanceof UDPEvent || event instanceof XMLEvent) {
			if (immediate) {
				event.status(Event.STATUS.QUEUED);
				queue(event);
				return response;
			}
			final Set<Method> delayedhandler = handlermap.get(fen + "Delayed");
			boolean nodelayedhandler = delayedhandler == null || (delayedhandler.isEmpty());
			final Set<Method> immediatehandler = handlermap.get(fen + "Immediate");
			boolean noimmediatehandler = immediatehandler == null || (immediatehandler.isEmpty());
			// what else could it be
			if (!warned.contains(fen) &&
					( nodelayedhandler ) &&
					( noimmediatehandler )
				) {
				log.log(FINE,"No handler for UDP/XML event {0}",fen);
				warned.add(fen);
			}
		}
		event.status(Event.STATUS.COMPLETE);
		return response;
	}

	/**
	 * Find the handler we associate with a method.
	 *
	 * @param method Method
	 *
	 * @return Handler that contains the method
	 */
	@Nonnull
	private Object findHandler(@Nonnull final Method method) {
		@Nonnull final Class<?> c=method.getDeclaringClass();
		for (@Nonnull final Handler h: brain) {
			if (h.getClass().equals(c)) { return h; }
		}
		throw new IllegalArgumentException("Could not find declaring class for "+method);
	}

	/**
	 * Search handlers for implementations of an event.
	 * Searches for Immediate and Delayed variations of UDP and XML events.
	 *
	 * @param event Event to scan for
	 */
	private void populateHandlerMap(final Event event) {
		if (event instanceof UDPEvent || event instanceof XMLEvent) {
			populateHandlerMap(event,"Immediate");
			populateHandlerMap(event,"Delayed");
		}
	}

	/**
	 * Populate the handler map for a type of event handler.
	 * Finds methods implementing the suffixed handler and stores the results in the handlermap.  Will store empty sets.
	 *
	 * @param event  Event to populate for
	 * @param suffix Suffix to search for, e.g. Immediate or Delayed
	 */
	private void populateHandlerMap(@Nonnull final Event event,
	                                final String suffix) {
		// find all the handlers that have a method like this and accumulate them into a set =)
		@Nonnull String fen=formatEventName(event);
		fen=fen+event.typeString();
		fen=fen+suffix;
		@Nonnull final Set<Method> methods=new HashSet<>();
		for (@Nonnull final Handler handler: brain) {
			try {
				if (event instanceof UDPEvent) { methods.add(handler.getClass().getMethod(fen,UDPEvent.class)); }
				if (event instanceof XMLEvent) { methods.add(handler.getClass().getMethod(fen,XMLEvent.class)); }
			}
			catch (@Nonnull final NoSuchMethodException ex) {
				// this is OK and probably the default case, not every module implements everything.
			}
			catch (@Nonnull final SecurityException ex) {
				// this is less OK
				log.log(WARNING,"Method {0} is inaccessible, this is probably unintentional",fen);
			}
		}
        /*System.out.print("Populating for '"+fen+"' found: ");
        for (Method m:methods) { System.out.print(m.getName()+" "); }
        System.out.println();*/
		handlermap.put(fen,methods);
	}

	private void callMaintenance() {
		for (@Nonnull final Handler h: brain) {
			try {h.maintenance();}
			catch (@Nonnull final Exception e) {
				log.log(SEVERE,"Handler "+h+" exceptioned during maintenance",e);
			}
		}
	}

	/**
	 * Called if we are spamming logins.
	 * Sleeps for 15 minutes.
	 */
	private void loginLoopSafetyViolation() {
		// probably need some choices here, sometimes it's probably appropriate to "exit" the class, perhaps via an 'error' of some kind that wont get caught
		// sometimes its probably appropriate to stop the whole system if the bot is critical (system.exit?)
		// sometimes the remainder of the application is more important and it should continue, and we should just sleep, which is what we do for now
		// no configuration here yet, hard coded 15 minute sleep, have fun with that.
		for (int i=15;i>0;i--) {
			log.severe("Reconnection Safety: RECONNECTION SAFETY HAS TRIPPED.  THREAD FORCE-SLEEPING FOR "+i+" MINUTES.");
			try { Thread.sleep(60000); } catch (@Nonnull final InterruptedException ignored) {}
		}
		log.warning("Reconnection Safety: Reconnection safety tripped, we have slept for 15 minutes, and will now return to attempting connections.");
	}

}
