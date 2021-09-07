package net.coagulate.JSLBot;

import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Messages.*;
import net.coagulate.JSLBot.Packets.Packet;
import net.coagulate.JSLBot.Packets.Types.*;
import org.apache.xmlrpc.XmlRpcException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.lang.annotation.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;
import static java.util.logging.Level.WARNING;

/**
 * Creates and runs a JSLBot.
 *
 * @author Iain Price
 */
public class JSLBot extends Thread {
	public final AtomicInteger bytesin=new AtomicInteger(0);
	public final AtomicInteger bytesout=new AtomicInteger(0);
	public final Map<Integer,Integer> messagebytesin=new HashMap<>();
	public final Map<Integer,Integer> messagebytesout=new HashMap<>();
	public final Integer startuptime=((int) Math.round(new Date().getTime()/1000.0));
	private final Logger log;
	@Nonnull
	private final Brain brain;
	private final Object connectsignal=new Object();
	private final Map<Long,Circuit> circuits=new HashMap<>();
	private final Object circuitsequencelock=new Object();
	public boolean registershutdownhook=true;
	public boolean ALWAYS_RECONNECT;
	Configuration config=new TransientConfiguration();
	JSLInterface jslinterface;
	// bot level data
	private String firstname;
	private String lastname;
	private String password;
	private String loginlocation;
	private String loginuri;
	private LLUUID sessionid;
	private LLUUID uuid;
	private int circuitcode;
	private boolean quit;
	private String quitreason="";
	private boolean reconnect;
	@Nullable
	private String homesickfor;
	@Nullable
	private LLUUID inventoryroot;
	// ********** BOT STARTUP **********
	private boolean connected;
	@Nullable
	private Date lastagentupdate;
	private float drawdistance=(float) 0.001;
	/**
	 * Send this generally useful message down the primary UDP circuit
	 */
	private boolean blind;
	/**
	 * Primary circuit, as in where the agent presence *is*
	 */
	@Nullable
	private Circuit primary;
	private float x;
	private float y;
	private float z;
	private float lx;
	private float ly;
	private float lz;
	private int fovgen;
	private int circuitsequence;
	public int controlflags;

	/**
	 * Create a bot based on configuration data.
	 *
	 * @param conf The configuration data object
	 */

	public JSLBot(@Nonnull final Configuration conf) {
		log=Logger.getLogger("net.coagulate.JSLBot."+conf.get("firstname")+" "+conf.get("lastname"));
		brain=new Brain(this);
		loadConf(conf);
	}

	// ---------- INSTANCE ----------
	public int getSecondsSinceStartup() {
		final int now=(int) Math.round(new Date().getTime()/1000.0);
		return now-startuptime;
	}

	public void dumpAccounting() {
		System.out.println("DUMP ACCOUNTING FOR BOT "+getFullName());
		synchronized (messagebytesin) {
			for (final Map.Entry<Integer,Integer> entry: messagebytesin.entrySet()) {
				System.out.println("Message ID : "+entry.getKey()+" received "+entry.getValue());
			}
		}
		synchronized (messagebytesout) {
			for (final Map.Entry<Integer,Integer> entry: messagebytesout.entrySet()) {
				System.out.println("Message ID : "+entry.getKey()+" transmitted "+entry.getValue());
			}
		}
	}

	@Nonnull
	public String getFullName() { return firstname+" "+lastname; }

	@Nonnull
	public String getUsername() { return firstname+"."+lastname; }

	public LLUUID getSession() { return sessionid; }

	public LLUUID getUUID() { return uuid; }

	public int getCircuitCode() { return circuitcode; }

	@Nonnull
	public Brain brain() {return brain;}

	public boolean quitting() { return quit; }

	public void setReconnect() { reconnect=true; }
	public void clearReconnect() { reconnect=false; }

	public void forceReconnect() {
		reconnect=true;
		shutdown("Forced to reconnect");
	}

	/**
	 * Instruct the bot to always reconnect when disconnected
	 */
	public void setAlwaysReconnect() {
		ALWAYS_RECONNECT=true;
		reconnect=true;
	}

	/**
	 * Instruct the bot to attempt to return home periodically
	 *
	 * @param regionname Name of region we have as home.  Bot will periodically teleport home if not in this region.
	 */
	public void homeSickFor(final String regionname) {
		log.info("Registered homesickness towards "+regionname);
		homesickfor=regionname;
	}


	// ********** LOGIN CODE / BOT PRIMITIVES **********

	@Nullable
	public String homeSickFor() { return homesickfor; }

	@Nullable
	public LLUUID getInventoryRoot() { return inventoryroot; }

	/**
	 * Get the JSLInterface API object for this bot.
	 *
	 * @return JSLInterface for this bot
	 */
	@Nonnull
	public JSLInterface api() {
		if (jslinterface==null) { throw new NullPointerException("JSLInterface not yet initialised"); }
		return jslinterface;
	}

	/**
	 * Reports if the bot is currently connected
	 *
	 * @return True if connected.
	 */
	public boolean connected() { return connected; }

	/**
	 * Wait up to a limited ammount of time for the bot to complete a connection.
	 *
	 * @param milliseconds Maximum time to wait for
	 *
	 * @throws IllegalStateException If we fail to connect due to timeout or being in a quit state.
	 */
	public void waitConnection(final long milliseconds) {
		if (quit) { throw new IllegalStateException("Quitting, can not wait on connection"); }
		if (connected) { return; }
		try {
			synchronized (connectsignal) { connectsignal.wait(milliseconds); }
		}
		catch (@Nonnull final InterruptedException ignored) {}
		if (connected) { return; }
		throw new IllegalStateException("Still not connected after timeout");
	}

	@Nonnull
	public Handler getHandler(final String name) { return brain.getHandler(name); }

	/**
	 * Launch bot AI.
	 * you can run this with Thread.start() if you want to run lots of bots
	 * if you just want to run this one bot, you can yield your main thread into here by calling JSLBot.run();
	 */
	@Override
	public void run() {
		log.info("JSLBot launching connection...");
		setName("JSLBot Brain for "+firstname+" "+lastname);
		if (registershutdownhook) { Runtime.getRuntime().addShutdownHook(new ShutdownHook(this)); }
		// catch and report.  "mainLoop()" should guard against everything its self so this means that function is broken
		// nominated for 'best line of code, 2016'
		// ^^ nominated for most usual comment for dating my intermittent work on this project, now mid 2018.
		if (brain.isEmpty()) { log.warning("Bot has no brain and will be a virtual zombie."); }
		reconnect=true;
		while (ALWAYS_RECONNECT || reconnect) {
			quit=false;
			quitreason="";
			connected=false;
			primary=null;
			reconnect=ALWAYS_RECONNECT;
			circuits.clear();
			brain.prepare();
			try { mainLoop(); } catch (@Nonnull final Exception e) { log.log(SEVERE,"Main bot loop crashed - "+e,e); }
			connected=false;
			shutdown("Exited");
			brain.loginLoopSafety();
		}
	}

	@Nonnull
	@Override
	public String toString() { return getFullName(); }

	public Logger getLogger(final String subspace) {
		return Logger.getLogger(log.getName()+"."+subspace);
	}

	/**
	 * Send this generally useful message down the primary UDP circuit
	 */
	public void completeAgentMovement() {
		final CompleteAgentMovement p=new CompleteAgentMovement();
		p.bagentdata.vagentid=getUUID();
		p.bagentdata.vsessionid=getSession();
		p.bagentdata.vcircuitcode=new U32(getCircuitCode());
		send(p);
	}

	/**
	 * Push an agent update
	 */
	public void forceAgentUpdate() { agentUpdate(true); }

	/**
	 * Send an agent update if one has not been sent recently
	 */
	public void agentUpdate() { agentUpdate(false); }


	// ********** TRANSMISSION PRIMITIVES **********

	public void blind() {
		blind=true;
		forceAgentUpdate();
	}

	public void unblind() {
		blind=false;
		forceAgentUpdate();
	}

	public void agentUpdate(final boolean force) {
		final boolean debug=false;
		if (quitting()) {
			return;
		}
		// don't spam too many of these
		if (!force && lastagentupdate!=null) {
			if ((new Date().getTime())-lastagentupdate.getTime()<Constants.AGENT_UPDATE_FREQUENCY_MILLISECONDS) {
				return;
			}
		}
		lastagentupdate=new Date();
		final AgentUpdate p=new AgentUpdate();
		p.bagentdata.vagentid=getUUID();
		p.bagentdata.vsessionid=getSession();
		final LLVector3 camera=getPos();
		camera.z+=5;
		if (blind) {
			p.bagentdata.vcameracenter=new LLVector3(192,144,9999);
			p.bagentdata.vcameraataxis=new LLVector3(0,1,0);
			p.bagentdata.vcameraleftaxis=new LLVector3(-1,0,0);
			p.bagentdata.vcameraupaxis=new LLVector3(0,0,1);
			p.bagentdata.vfar=new F32((float) 0.001);
		}
		else {
			p.bagentdata.vcameracenter=camera;
			p.bagentdata.vcameraataxis=new LLVector3(0,1,0);
			p.bagentdata.vcameraleftaxis=new LLVector3(-1,0,0);
			p.bagentdata.vcameraupaxis=new LLVector3(0,0,1);
			p.bagentdata.vfar=new F32(drawdistance);
		}
		p.bagentdata.vcontrolflags=new U32(controlflags);
		// FIXME CHECK THIS
        /*if (Math.random()>0.5) { p.bagentdata.vcontrolflags=new U32(1<<26); }
        p.bagentdata.vbodyrotation.x=(float) (Math.random()*Math.PI*2);
        p.bagentdata.vbodyrotation.y=(float) (Math.random()*Math.PI*2);
        p.bagentdata.vbodyrotation.z=(float) (Math.random()*Math.PI*2);
        p.bagentdata.vheadrotation.x=(float) (Math.random()*Math.PI*2);
        p.bagentdata.vheadrotation.y=(float) (Math.random()*Math.PI*2);
        p.bagentdata.vheadrotation.z=(float) (Math.random()*Math.PI*2);
        p.bagentdata.vcameraataxis.x=1;
        p.bagentdata.vcameraleftaxis.y=1;
        p.bagentdata.vcameraupaxis.z=y;*/
		send(p);
		//debug("Agent Updated");
	}

	/**
	 * Send an instant message immediately using the primary circuit
	 *
	 * @param uuid    UUID of agent to send message to
	 * @param message Message to send
	 */
	public void im(final LLUUID uuid,
	               @Nonnull final String message) {
		final ImprovedInstantMessage reply=new ImprovedInstantMessage(this);
		reply.bmessageblock.vtoagentid=uuid;
		reply.bmessageblock.vmessage=new Variable2(message);
		send(reply,true);
	}

	/**
	 * Get the name of the region the avatar is present in.
	 *
	 * @return Region name
	 */
	public String getRegionName() { return circuit().getRegionName(); }

	/**
	 * Send a packet.
	 *
	 * @param p Packet to send
	 */
	public void send(@Nonnull final Packet p) {
		if (primary==null) { throw new IllegalStateException("Primary circuit is not defined or connected"); }
		primary.send(p);
	}

	/**
	 * Send a Message, optionally reliably
	 *
	 * @param m        Message to send
	 * @param reliable If set, use reliable mode
	 */
	public void send(final Message m,
	                 final boolean reliable) {
		final Packet p=new Packet(m);
		p.setReliable(reliable);
		send(p);
	}

	/**
	 * Send a message, without reliable flag.
	 *
	 * @param m Message to send.
	 */
	public void send(final Message m) {send(m,false); }

	/**
	 * Change the primary circuit.
	 * Mainly used by the Teleporation Handler.
	 *
	 * @param c The new primary circuit.
	 */
	public void setPrimaryCircuit(final Circuit c) { primary=c; }

	/**
	 * Get the primary circuit's CAPS.
	 *
	 * @return CAPS object for the avatar's region.
	 */
	@Nonnull
	public CAPS getCAPS() { return circuit().getCAPS(); }

	/**
	 * Resolve a UUID into a firstname, either via cache or via lookup
	 *
	 * @param uuid UUID to look up
	 *
	 * @return The first name
	 */
	@Nullable
	public String getFirstName(@Nonnull final LLUUID uuid) {
		if (uuid.equals(new LLUUID())) { return "NOUUID"; }
		if (Global.firstName(uuid)==null) {
			try { getCAPS().getNames(uuid); }
			catch (@Nonnull final IOException e) {
				log.log(WARNING,"Failed to lookup agent names",e);
			}
		}
		if (Global.firstName(uuid)==null) { Global.firstName(uuid,"???"); }
		return Global.firstName(uuid);
	}

	/**
	 * Resolve a UUID into lastname, either via cache or via lookup
	 *
	 * @param uuid UUID to look up
	 *
	 * @return The last name
	 */
	@Nullable
	public String getLastName(@Nonnull final LLUUID uuid) {
		if (uuid.equals(new LLUUID())) { return "NOUUID"; }
		if (Global.lastName(uuid)==null) {
			try { getCAPS().getNames(uuid); }
			catch (@Nonnull final IOException e) {
				log.log(WARNING,"Failed to lookup agent names",e);
			}
		}
		if (Global.lastName(uuid)==null) { Global.lastName(uuid,"???"); }
		return Global.lastName(uuid);
	}

	/**
	 * Resolve a UUID into a username, either via cache or via lookup
	 *
	 * @param uuid UUID to look up
	 *
	 * @return User name
	 */
	@Nullable
	public String getUserName(@Nonnull final LLUUID uuid) {
		if (uuid.equals(new LLUUID())) { return "NOUUID"; }
		if (Global.userName(uuid)==null) {
			try { getCAPS().getNames(uuid); }
			catch (@Nonnull final IOException e) {
				log.log(WARNING,"Failed to lookup agent names",e);
			}
		}
		if (Global.userName(uuid)==null) { Global.userName(uuid,"???"); }
		return Global.userName(uuid);
	}

	/**
	 * Resolve a UUID into a displayname, either via cache or via lookup
	 *
	 * @param uuid UUID to look up
	 *
	 * @return Display name
	 */
	@Nullable
	public String getDisplayName(@Nonnull final LLUUID uuid) {
		if (uuid.equals(new LLUUID())) { return "NOUUID"; }
		if (Global.displayName(uuid)==null) {
			try { getCAPS().getNames(uuid); }
			catch (@Nonnull final IOException e) {
				log.log(WARNING,"Failed to lookup agent names",e);
			}
		}
		if (Global.displayName(uuid)==null) { Global.displayName(uuid,"???"); }
		return Global.displayName(uuid);
	}

	/**
	 * Obtain a circuit to the target.
	 * If a live circuit already exists for this handle, that is returned, otherwise a new circuit is created and started.
	 * Note the target sim must be expecting us.
	 *
	 * @param numericip Sim IP address
	 * @param port      Sim IP port
	 * @param handle    Region handle
	 * @param capsurl   CAPS url for target region, potentially null for child agents which get this later.
	 *
	 * @return Activated circuit for requested region handle
	 *
	 * @throws IOException If the circuit fails to connect.
	 */
	public Circuit createCircuit(final String numericip,
	                             final int port,
	                             final long handle,
	                             final String capsurl) throws IOException {
		synchronized (circuits) {
			if (circuits.containsKey(handle)) {
				if (circuits.get(handle).isAlive()) {
					// already got a circuit
					if (Debug.CIRCUIT) { log.fine("Duplicate circuit to "+handle+" ignored"); }
					return circuits.get(handle);
				}
			}
			if (Debug.CIRCUIT) { log.fine("New circuit to "+handle); }
			final Circuit newcircuit=new Circuit(this,numericip,port,handle,capsurl);
			newcircuit.connect();
			circuits.put(handle,newcircuit);
			return newcircuit;
		}
	}

	/**
	 * Get the regional info for the primary region
	 *
	 * @return Primary region the avatar is present in
	 */
	@Nonnull
	public Regional getRegional() {
		return circuit().regional();
	}

	/**
	 * Get regional data for all connected circuit
	 *
	 * @return Get all connected regionals
	 */
	@Nonnull
	public Set<Regional> getRegionals() {
		final Set<Regional> regionalset=new HashSet<>();
		synchronized (circuits) {
			for (final Circuit circuit: circuits.values()) {
				//System.out.println("Circuit "+handle);
				regionalset.add(circuit.regional());
			}
		}
		return regionalset;
	}

	/**
	 * Get all circuits
	 *
	 * @return Set of all circuits
	 */
	@Nonnull
	public Set<Circuit> getCircuits() {
		synchronized (circuits) {
			return new HashSet<>(circuits.values());
		}
	}

	/**
	 * Initiate disconnection from SL
	 *
	 * @param reason Reason for disconnecting.
	 */
	public void shutdown(final String reason) {
		connected=false;
		if (quit) { return; } // do not re-enter
		quit=true;
		quitreason=reason;
		log.warning("Shutdown requested: "+reason);
		// because we'll get concurrent modification exceptions otherwise, as we close the circuits while iterating.
		final Set<Circuit> closeme=new HashSet<>(getCircuits());
		for (final Circuit c: closeme) {
			try { c.close(); } catch (@Nonnull final Exception ignored) {}
		}
		getCircuits().clear();
		brain.stopProcrastinating();  // release the main thread
	}

	@Nonnull
	public LLVector3 getPos() { return new LLVector3(x,y,z); }

	public void setPos(@Nonnull final LLVector3 p) {
		x=p.x;
		y=p.y;
		z=p.z;
	}

	public void setPos(final float x,
	                   final float y,
	                   final float z) {
		this.x=x;
		this.y=y;
		this.z=z;
	}

	@Nonnull
	public LLVector3 getLookAt() { return new LLVector3(lx,ly,lz); }

	public void setLookAt(@Nonnull final LLVector3 l) {
		lx=l.x;
		ly=l.y;
		lz=l.z;
	}

	public void setLookAt(final float x,
	                      final float y,
	                      final float z) {
		lx=x;
		ly=y;
		lz=z;
	}

	public void setFOV(final float angle) {
		final AgentFOV fov=new AgentFOV();
		fov.bagentdata.vagentid=getUUID();
		fov.bagentdata.vcircuitcode=new U32(getCircuitCode());
		fov.bagentdata.vsessionid=getSession();
		fov.bfovblock.vgencounter=new U32(fovgen++);
		fov.bfovblock.vverticalangle=new F32(angle);
		send(fov,true);
	}

	public void setMaxFOV() { setFOV((float) (Math.PI)); }

	public void setMinFOV() { setFOV((float) 0.01); }

	public float drawDistance() { return drawdistance; }

	public void drawDistance(final float newdd) {
		drawdistance=newdd;
		forceAgentUpdate();
	}

	// ----- Internal Instance -----
	void accountMessageIn(final int id,
	                      final int length) {
		synchronized (messagebytesin) {
			int sum=0;
			if (messagebytesin.containsKey(id)) { sum=messagebytesin.get(id); }
			sum+=length;
			messagebytesin.put(id,sum);
		}
	}

	void accountMessageOut(final int id,
	                       final int length) {
		synchronized (messagebytesout) {
			int sum=0;
			if (messagebytesout.containsKey(id)) { sum=messagebytesout.get(id); }
			sum+=length;
			messagebytesout.put(id,sum);
		}
	}

	/**
	 * Send this generally useful message down the primary UDP circuit
	 */
	@Nonnull
	Packet useCircuitCode() {
		final UseCircuitCode cc=new UseCircuitCode();
		cc.bcircuitcode.vcode=new U32(getCircuitCode());
		cc.bcircuitcode.vsessionid=getSession();
		cc.bcircuitcode.vid=getUUID();
		final Packet p=new Packet(cc);
		p.setReliable(true);
		return p;
	}

	@Nonnull
	Circuit circuit() {
		if (primary==null) { throw new IllegalStateException("Primary circuit is not yet set up"); }
		return primary;
	}

	/**
	 * Inform bot that a circuit closed.
	 * If this is our primary (non child agent) circuit we're in trouble and will quit.
	 *
	 * @param regionhandle Region handle that's closing connection.
	 * @param circuit         Associated circuit, used as a 'check' only
	 */
	void deregisterCircuit(final Long regionhandle,
	                       final Circuit circuit) {
		synchronized (circuits) {
			final Circuit c=circuits.get(regionhandle);
			if (c!=null) { c.close(); }
			if (circuit!=c && c!=null) {
				log.severe("Closing a region handle but the circuit is not the one we have registered");
			}
			circuits.remove(regionhandle);
			// don't warn if shutting down
			if (!quit && c==primary) {
				log.severe("Closure of primary circuit detected, this is fatal?");
				shutdown("Primary circuit lost, we have been disconnected?");
			}
		}
	}

	/**
	 * Get the circuit for a given region handle
	 *
	 * @param regionhandle Region handle to query
	 *
	 * @return Circuit for the region handle, or null
	 */
	Circuit getCircuit(final Long regionhandle) {
		return circuits.get(regionhandle);
	}

	int getCircuitSequence() {
		synchronized (circuitsequencelock) {
			circuitsequence++;
			return circuitsequence;
		}
	}

	private void loadConf(final Configuration conf) {
		// load from config and call 'setup'
		config=conf;
		String location=config.get("loginlocation");
		if (location==null || "".equals(location)) { location="home"; }  // default to home
		//String potentialmaster=config.get("owner");

		final String handlerlist=config.get("handlers","");
		brain.loadHandlers(handlerlist.split(","));
		String uri=config.get("loginuri");
		if (uri==null || uri.isEmpty()) { uri="https://login.agni.lindenlab.com/cgi-bin/login.cgi"; }
		setup(config.get("firstname"),config.get("lastname"),config.get("password"),location,uri);
		log.info("JSLBot initialisation complete, ready to launch");
	}

	private void setup(final String firstname,
	                   final String lastname,
	                   final String password,
	                   final String loginlocation,
					   @Nonnull final String loginuri) {
		jslinterface=new JSLInterface(this);
		LLCATruster.initialise(); // probably compromises the SSL engine in various ways :(
		this.firstname=firstname;
		this.lastname=lastname;
		this.password=password;
		this.loginlocation=loginlocation;
		this.loginuri=loginuri;
		log.config(Constants.getVersion());
	}

	// Wrapper for logging in, implements retries and backoff.
	private void performLogin(final String firstname,
	                          final String lastname,
	                          @Nonnull final String password,
	                          final String location,
							  @Nonnull final String loginuri) throws Exception {
		Exception last=null;
		for (int retries=0;retries<Constants.MAX_RETRIES;retries++) {
			try {
				login(firstname,lastname,password,location,loginuri);
				return;
			}
			catch (@Nonnull final RuntimeException|IOException|XmlRpcException e) {
				last=e;
				long delay=Constants.RETRY_INTERVAL*retries;
				if (delay>Constants.MAX_RETRY_INTERVAL) { delay=Constants.MAX_RETRY_INTERVAL; }
				if (e instanceof NullPointerException) {
					log.log(SEVERE,"Unexpected null pointer exception during login",e);
				}
				else {
					log.info("Login attempt "+(retries+1)+"/"+Constants.MAX_RETRIES+" failed: "+e.getClass().getSimpleName()+":"+e.getMessage());
				}
				try { if (!quit) { Thread.sleep(delay); } } catch (@Nonnull final InterruptedException ignored) {}
			}
			if (quit) { return; }
		}
		log.severe("All login attempts failed!");
		throw new IOException("Failed login",last);
	}

	// Perform a login attempt
	private void login(final String firstname,
	                   final String lastname,
	                   @Nonnull final String password,
	                   final String loginlocation,
					   @Nonnull final String loginuri) throws IOException, XmlRpcException {
		// authentication is performed over XMLRPC over HTTPS
		final Map<Object,Object> result=BotUtils.loginXMLRPC(this,firstname,lastname,password,loginlocation,loginuri);
		if (!("true".equalsIgnoreCase((String) result.get("login")))) {
			throw new IOException("Server gave error: "+result.get("message"));
		}
		final String message=(String) result.get("message");
		log.info("Login MOTD: "+message);

		// the response contains things we'll need
		final String fn=(String) result.get("first_name");
		this.firstname=fn.substring(1,fn.length()-1);
		this.lastname=(String) result.get("last_name");
		uuid=new LLUUID((String) result.get("agent_id"));
		// probably want to note the "udp_blacklist" which is a comma separated list of packet types to not use.  but then if we just aren't using them either...?
		circuitcode=(int) result.get("circuit_code");
		sessionid=new LLUUID((String) result.get("session_id"));
		final String ip=(String) result.get("sim_ip");
		final int port=(Integer) result.get("sim_port");
		final int loginx=(Integer) result.get("region_x");
		final int loginy=(Integer) result.get("region_y");
		final Object[] inventoryrootarray=(Object[]) result.get("inventory-root");
		@SuppressWarnings("unchecked") final // if it isn't, what do we do anyway?
				Map<String,String> rootmap=(Map<String,String>) inventoryrootarray[0];
		for (final Map.Entry<String,String> entry: rootmap.entrySet()) {
			if (Debug.AUTH) {
				log.finer("Inventory Root "+entry.getKey()+" = "+entry.getValue());
			}
			inventoryroot=new LLUUID(entry.getValue());
		}
		//System.out.println("inventoryroot type is "+inventoryroot.getClass().getName());
		// derive region handle
		final U64 handle=new U64();
		handle.value=loginx;
		handle.value=handle.value<<(32);
		handle.value=handle.value|(loginy);
		if (Debug.AUTH || Debug.REGIONHANDLES) {
			log.finer("Computed initial handle of "+Long.toUnsignedString(handle.value));
		}
		// caps
		final String seedcaps=(String) result.get("seed_capability");
		log.info("Login is complete, opening initial circuit.");
		final Circuit initial=new Circuit(this,ip,port,handle.value,seedcaps);
		initial.connect();
		// for our main connection, "move in" to the sim, it's expecting us :P
		primary=initial;
		circuits.put(handle.value,initial);
		completeAgentMovement();
		agentUpdate();
		connected=true;
		synchronized (connectsignal) { connectsignal.notifyAll(); } // wake up, sleepers
	}

	// post login main loop, update + think in a loop, until we're quitting (disconnecting)
	private void mainLoop() throws Exception {
		performLogin(firstname,lastname,password,loginlocation,loginuri);
		if (!quit) { brain.loggedIn(); }
		if (!config.get("homeseat","").isBlank())
		{
			Map<String,String> args=new HashMap<>();
			args.put("uuid",config.get("homeseat"));
			CommandEvent sit=new CommandEvent(this,null,"siton",args,new LLUUID(config.get("CnC.authorisation.owneruuid")));
			brain.execute(sit);
		}
		while (!quit) {
			//agentUpdate();
			brain.think();
		}
		log.warning("Bot exited: "+quitreason);
	}

	/**
	 * Describes a command.
	 * Commands must have this annotation.
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@Target(ElementType.METHOD)
	public @interface CmdHelp {

		// ---------- INSTANCE ----------

		/**
		 * Get the description for this command
		 *
		 * @return This command's description
		 */
		@Nonnull String description();
	}

	/**
	 * Describes an argument to a command.
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@Target(ElementType.PARAMETER)
	public @interface Param {

		// ---------- INSTANCE ----------

		/** Name of this parameter
		 *
		 * @return The parameters name
		 */
		@Nonnull String name();
		/**
		 * Description for this parameter
		 *
		 * @return This parameter's description.
		 */
		@Nonnull String description();
	}

	private static class ShutdownHook extends Thread {
		final JSLBot bot;

		ShutdownHook(final JSLBot bot) {this.bot=bot;}

		// ---------- INSTANCE ----------
		@Override
		public void run() {bot.shutdown("JVM called shutdown hook (program terminated?)");}
	}

	public String getHomeSeat() {
		String ret=config.get("homeseat","");
		if (ret==null) { ret=""; }
		return ret;
	}

	public Configuration getConfig() { return config; }
}
