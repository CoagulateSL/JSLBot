package net.coagulate.JSLBot;

import net.coagulate.JSLBot.Packets.Types.LLUUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static java.util.logging.Level.INFO;

/**
 * An event that represents an invoked command.
 * Note there are two execution types, submit (delayed mode) and execute (immediate mode).
 * As always, know what you're doing with immediate mode execution.
 *
 * @author Iain Price
 */
public class CommandEvent extends Event {
	private final Logger log;
	// K=V style parameters, typeless
	private final Map<String,String> parameters;
	// auto IM the response to
	private final LLUUID respondto;
	// the response its self, for other threads to read.
	@Nullable
	private String response="";
	private boolean immediate;
	// for evaluating the 'authorisation' of this command
	@Nullable
	private String invokerusername;
	@Nullable
	private LLUUID invokeruuid;

	public CommandEvent(@Nonnull final JSLBot bot,
	                    @Nonnull final Regional r,
	                    @Nonnull final String name,
	                    final Map<String,String> parameters,
	                    final LLUUID respondto) {
		super(bot,r,name.toLowerCase());
		log=bot.getLogger("CommandEvent."+name);
		this.parameters=parameters;
		this.respondto=respondto;
	}

	// ---------- INSTANCE ----------
	public Map<String,String> parameters() {return parameters;}

	public LLUUID respondTo() { return respondto; }

	public void respondTo(@Nonnull final String response) { this.response=response; }

	@Nullable
	public String response() { return response; }

	@Nullable
	public String invokerUsername() { return invokerusername; }

	public void invokerUsername(final String invoker) { invokerusername=invoker; }

	@Nullable
	public LLUUID invokerUUID() { return invokeruuid; }

	public void invokerUUID(final LLUUID invokeruuid) { this.invokeruuid=invokeruuid; }

	@Nonnull
	@Override
	public String dump() {
		final StringBuilder ret=new StringBuilder("COMMAND: "+getName());
		for (final Map.Entry<String,String> entry: parameters.entrySet()) {
			ret.append("\n").append(entry.getKey()).append("=").append(entry.getValue());
		}
		return ret.toString();
	}

	/**
	 * Submits this command onto the queue for DELAYED processing by the AI thread.
	 * This is the most normal way to do things unless you know better.
	 */
	@Override
	public void submit() {
		immediate(false);
		bot().brain().queue(this);
	}

	/**
	 * Submits the command for DELAYED processing, then halts this thread until it completes or times out
	 *
	 * @param timeoutmillis Number of milliseconds before giving up waiting
	 *
	 * @return Command response
	 */
	@Nullable
	public String submitAndWait(final long timeoutmillis) {
		submit();
		waitFinish(timeoutmillis);
		return response();
	}

	/**
	 * Submits the command for Delayed processing and waits 10 seconds for it to complete
	 *
	 * @return The final response from the command.
	 */
	@Nullable
	public String submitAndWait() { return submitAndWait(10000); }

	/**
	 * Submits the command for immediate execution, the thread calling this function will be hijacked to execute the command.
	 * There are sometimes very good reasons for this, and sometimes its definitely the wrong thing to do.  If you're not sure, use submit().
	 *
	 * @return Command response
	 */
	@Nullable
	public String execute() {
		immediate(true);
		return bot().brain().execute(this);
	}

	// ----- Internal Instance -----
	void response(@Nullable final String response) { this.response=response; }

	boolean immediate() { return immediate; }

	@Nullable
	String run(@Nonnull final Object callon,
	           @Nonnull final Method handler) {
		if (Debug.TRACKCOMMANDS) {
			log.fine("Entering run() for "+handler.getName()+" in class "+callon.getClass().getSimpleName());
		}
		try {
			return (String) handler.invoke(callon,getParameters(handler).toArray());
		}
		catch (@Nonnull final IllegalAccessException|IllegalArgumentException ex) {
			throw new AssertionError("Error accessing "+handler.getName()+" in class "+callon.getClass().getName(),ex);
		}
		catch (@Nonnull final InvocationTargetException ex) {
			Throwable t=ex;
			if (t.getCause()!=null) { t=t.getCause(); }
			log.log(INFO,"Handler "+handler.getName()+" in class "+callon.getClass().getSimpleName()+" threw exception "+BotUtils.unravel(t),t);
			return "Exception inside handler "+handler.getName()+BotUtils.unravel(t);
		}
	}

	private void immediate(final boolean immediate) { this.immediate=immediate; }

	@Nonnull
	private List<Object> getParameters(@Nonnull final Method method) {
		final List<Object> params=new ArrayList<>();
		params.add(this);
		boolean firstparam=true;
		for (final Parameter param: method.getParameters()) {
			//System.out.println(param.toString());
			if (firstparam) { firstparam=false; }
			else {
				final String paramname=param.getName();
				params.add(parameters().getOrDefault(paramname,null));
			}
		}
		return params;
	}

}
