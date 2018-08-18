package net.coagulate.JSLBot;
import java.io.*;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.net.MalformedURLException;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.*;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Messages.*;
import net.coagulate.JSLBot.Packets.Packet;
import net.coagulate.JSLBot.Packets.Types.*;
import org.apache.xmlrpc.XmlRpcException;

/** Creates and runs a JSLBot.
 *
 * @author Iain Price
 */
public class JSLBot extends Thread {
    // bot level data
    private String firstname;
    private String lastname;
    public String getFullName() { return firstname+" "+lastname; }
    public String getUsername() { return firstname+"."+lastname; }

    private String password;
    private String loginlocation;
    private LLUUID sessionid;
    public LLUUID getSession() { return sessionid; }
    private LLUUID uuid; public LLUUID getUUID() { return uuid; }
    private int circuitcode; public int getCircuitCode() { return circuitcode; }
    
    private final Brain brain=new Brain(this);
    public Brain brain(){return brain;}
    
    Configuration config=new TransientConfiguration();

    private boolean quit=false; public boolean quitting() { return quit; }
    private String quitreason="";
    private boolean reconnect=false; public void setReconnect() { reconnect=true; }
    public void forceReconnect() { reconnect=true; shutdown("Forced to reconnect"); }
    public boolean ALWAYS_RECONNECT=false; public void setAlwaysReconnect() { ALWAYS_RECONNECT=true; reconnect=true;  }
    JSLInterface jslinterface; public JSLInterface api() { return jslinterface; }

    // create a bot from a configuration store
    public JSLBot(Configuration conf) throws Exception {
        loadConf(conf);
    }
    
    private void loadConf(Configuration conf) throws Exception {
        // load from config and call 'setup'
        config=conf; 
        String location=config.get("loginlocation");
        if (location==null || location.equals("")) { location="home"; }  // default to home
        //String potentialmaster=config.get("owner");
        
        String handlerlist=config.get("handlers","");
        brain.loadHandlers(handlerlist.split(","));
        setup(config.get("firstname"),config.get("lastname"),config.get("password"),location);
        note("JSLBot initialisation complete, ready to launch");
    }
    // alternative convenience instansiators
    public JSLBot(String firstname,String lastname,String password) throws Exception { setup(firstname,lastname,password,"home"); }
    public JSLBot(String firstname,String lastname,String password,String loginlocation) throws Exception { setup(firstname,lastname,password,loginlocation); }
    
    
    // ********** BOT STARTUP **********
    private boolean connected=false; public boolean connected() { return connected; }
    private final Object connectsignal=new Object();
    /** Wait up to a limited ammount of time for the bot to complete a connection.
     * 
     * @param milliseconds Maximum time to wait for
     * @throws IllegalStateException If we fail to connect due to timeout or being in a quit state. 
     */
    public void waitConnection(long milliseconds) throws IllegalStateException {
        if (quit) { throw new IllegalStateException("Quitting, can not wait on connection"); }
        if (connected) { return; }
        try {
            synchronized(connectsignal) { connectsignal.wait(milliseconds); }                
        } catch (InterruptedException e) {}
        if (connected) { return; }
        throw new IllegalStateException("Still not connected after timeout");
    }
    
    private void setup(String firstname,String lastname,String password,String loginlocation) throws CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException, NoSuchMethodException {        
        // test that method names are preserved
        String argname=this.getClass().getDeclaredMethod("setup",String.class,String.class,String.class,String.class).getParameters()[0].getName();
        if (argname.equals("arg0") || (!argname.equals("firstname"))) {
            System.out.println("===== FATAL ERROR =====");
            System.out.println("The name of the first method for setup() is "+argname);
            System.out.println("In the source this is called 'firstname'");
            System.out.println("JSLBot uses reflection to read parameter names to implement bot commands");
            System.out.println("It is necessary to include parameter names in the compiled bytecode");
            System.out.println("This is generally done by compiling (javac) with the '-g' and '-parameters' option to enable debug info");
            System.out.println("Command functionality will not work without this feature, the bot has been aborted for safety reasons");
            System.out.println("NetBeans note: Add to compiler additional options and DISABLE compile on save which ignores these settings");
            throw new AssertionError("Invalid JSLBot compilation, expected argument name 'newbrain', got '"+argname+"'");
        }
        
        jslinterface=new JSLInterface(this);
        LLCATruster.initialise(); // probably compromises the SSL engine in various ways :(
        this.firstname=firstname;
        this.lastname=lastname;
        this.password=password;
        this.loginlocation=loginlocation;
        Log.note(this,Constants.getVersion());
    }


    public Handler getHandler(String name) { return brain.getHandler(name); }
    
    /** Launch bot AI.
     * you can run this with Thread.start() if you want to run lots of bots
     * if you just want to run this one bot, you can yield your main thread into here by calling JSLBot.run();
     */
    @Override
    public void run() {
        note("JSLBot launching connection...");        
        setName("JSLBot Brain for "+firstname+" "+lastname);
        Runtime.getRuntime().addShutdownHook(new ShutdownHook(this));
        // catch and report.  "mainLoop()" should guard against everything its self so this means that function is broken
        // nominated for 'best line of code, 2016'
        // ^^ nominated for most useul comment for dating my intermittent work on this project, now mid 2018.
        if (brain.isEmpty()) { warn("Bot has no brain and will be a virtual zombie."); }
        reconnect=true;
        while (ALWAYS_RECONNECT || reconnect) {
            quit=false; quitreason=""; connected=false; primary=null;
            reconnect=ALWAYS_RECONNECT;
            circuits.clear();
            brain.prepare();
            try { mainLoop(); }
            catch (Exception e) { error("Main bot loop crashed - "+e.toString(),e); }
            brain.loginLoopSafety();
        }
    }

    private class ShutdownHook extends Thread {
        JSLBot bot;
        ShutdownHook(JSLBot bot) {this.bot=bot;}
        @Override
        public void run() {bot.shutdown("JVM called shutdown hook (program terminated?)");}
    }
    
    /** Wrapper for logging in, implements retries and backoff. */
    private void performLogin(String firstname,String lastname,String password,String location) throws Exception {
        Exception last=null;
        for (int retries=0;retries<Constants.MAX_RETRIES;retries++) {
            try { login(firstname,lastname,password,location); return; }
            catch (RuntimeException | IOException | NoSuchAlgorithmException | XmlRpcException e) {
                last=e;
                long delay = Constants.RETRY_INTERVAL*retries;
                if (delay>Constants.MAX_RETRY_INTERVAL) { delay=Constants.MAX_RETRY_INTERVAL; }
                note("Login attempt "+(retries+1)+"/"+Constants.MAX_RETRIES+" failed: "+e.getMessage());
                try { Thread.sleep(delay); } catch (InterruptedException f) {}
            }
        }
        crit("All login attempts failed!");
        throw new IOException("Failed login",last);
    }
    
    
    
    
    
    
    
    // ********** LOGIN CODE / BOT PRIMITIVES **********
    
    /** Perform a login attempt */
    private void login(String firstname,String lastname,String password,String loginlocation) throws MalformedURLException, IOException, NoSuchAlgorithmException, XmlRpcException  {
        // authentication is performed over XMLRPC over HTTPS
        Map result=BotUtils.loginXMLRPC(this,firstname, lastname, password, loginlocation);
        if (!(((String)result.get("login")).equalsIgnoreCase("true"))) {
            throw new IOException("Server gave error: "+((String)result.get("message")));
        }
        String message=(String)result.get("message");
        note("Login MOTD: "+message);

        // the response contains things we'll need
        String fn=(String)result.get("first_name");
        this.firstname=fn.substring(1,fn.length()-1);
        this.lastname=(String)result.get("last_name");
        uuid=new LLUUID((String)result.get("agent_id"));
        // probably want to note the "udp_blacklist" which is a comma separated list of packet types to not use.  but then if we just aren't using them either...?
        circuitcode=(int)result.get("circuit_code");
        sessionid=new LLUUID((String)result.get("session_id"));
        String ip=(String)result.get("sim_ip");
        int port=(Integer)result.get("sim_port");
        int loginx=(Integer)result.get("region_x");
        int loginy=(Integer)result.get("region_y");
        // derive region handle
        U64 handle=new U64();
        handle.value=loginx;
        handle.value=handle.value<<(32);
        handle.value=handle.value | (loginy);     
        if (Debug.AUTH || Debug.REGIONHANDLES) { debug("Computed initial handle of "+Long.toUnsignedString(handle.value)); }
        // caps
        String seedcaps=(String)result.get("seed_capability");
        info("Login is complete, opening initial circuit.");
        Circuit initial=new Circuit(this, ip, port, handle.value,seedcaps);
        initial.connect();
        // for our main connection, "move in" to the sim, it's expecting us :P
        primary=initial;
        circuits.put(handle.value, initial);
        completeAgentMovement();
        agentUpdate();
        connected=true;
        synchronized(connectsignal) { connectsignal.notifyAll(); } // wake up, sleepers
    }

    /** Send this generally useful message down the primary UDP circuit */
    public void completeAgentMovement() {
        CompleteAgentMovement p=new CompleteAgentMovement();
        p.bagentdata.vagentid=getUUID();
        p.bagentdata.vsessionid=getSession();
        p.bagentdata.vcircuitcode=new U32(getCircuitCode());
        send(p);
    }
    private Date lastagentupdate=null;
    private float drawdistance=(float) 64.0;
    public void forceAgentUpdate() { agentUpdate(true); }
    public void agentUpdate() { agentUpdate(false); }
    /** Send this generally useful message down the primary UDP circuit
     * @param force */
    public void agentUpdate(boolean force) {
        if (quitting()) { return; }
        // dont spam too many of these
        if (!force && lastagentupdate!=null) {
            if ((new Date().getTime())-lastagentupdate.getTime()<Constants.AGENT_UPDATE_FREQUENCY_MILLISECONDS) { return; }
        }
        lastagentupdate=new Date();
        AgentUpdate p=new AgentUpdate();
        p.bagentdata.vagentid=getUUID();
        p.bagentdata.vsessionid=getSession();
        LLVector3 camera = getPos();
        camera.z+=5;
        p.bagentdata.vcameracenter=camera;
        p.bagentdata.vfar=new F32(drawdistance);
        if (Math.random()>0.5) { p.bagentdata.vcontrolflags=new U32(1<<26); }
        p.bagentdata.vbodyrotation.x=(float) (Math.random()*Math.PI*2);
        p.bagentdata.vbodyrotation.y=(float) (Math.random()*Math.PI*2);
        p.bagentdata.vbodyrotation.z=(float) (Math.random()*Math.PI*2);
        p.bagentdata.vheadrotation.x=(float) (Math.random()*Math.PI*2);
        p.bagentdata.vheadrotation.y=(float) (Math.random()*Math.PI*2);
        p.bagentdata.vheadrotation.z=(float) (Math.random()*Math.PI*2);
        p.bagentdata.vcameraataxis.x=1;
        p.bagentdata.vcameraleftaxis.y=1;
        p.bagentdata.vcameraupaxis.z=y;
        send(p);
        //debug("Agent Updated");
    }

    /** Send this generally useful message down the primary UDP circuit */
    Packet useCircuitCode() {
        UseCircuitCode cc=new UseCircuitCode();
        cc.bcircuitcode.vcode=new U32(getCircuitCode());
        cc.bcircuitcode.vsessionid=getSession();
        cc.bcircuitcode.vid=getUUID();
        Packet p=new Packet(cc);
        p.setReliable(true);
        return p;
    }


    /** Send an instant message immediately using the primary circuit
     * @param uuid
     * @param message */
    public void im(LLUUID uuid,String message) {
        ImprovedInstantMessage reply=new ImprovedInstantMessage(this);
        reply.bmessageblock.vtoagentid=uuid;
        reply.bmessageblock.vmessage=new Variable2(message);
        send(reply,true);
    }    

    
    
    // ********** TRANSMISSION PRIMITIVES **********
    /** Primary circuit, as in where the agent presence *is* */
    Circuit primary=null;  Circuit circuit() { return primary; }
    public String getRegionName() { return circuit().getRegionName(); }
    
    public void send(Packet p) { primary.send(p); }
    public void send(Message m,boolean reliable) {
        Packet p=new Packet(m);
        p.setReliable(reliable);
        send(p);
    }
    public void send(Message m) {send(m,false); }
    public void setPrimaryCircuit(Circuit c) { primary=c; }
    

    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Target(ElementType.METHOD)
    public @interface CmdHelp {
        String description();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Target(ElementType.PARAMETER)
    public @interface ParamHelp {
        String description();
    }













    public void mainLoop() throws Exception {
        performLogin(firstname,lastname,password,loginlocation);
        brain.loggedIn();
        while (!quit) {
            agentUpdate();
            brain.think();
        }
        note("Bot exited: "+quitreason);
    }

    public CAPS getCAPS() { return primary.getCAPS(); }
    /** Resolve a UUID into a firstname, either via cache or via lookup
     * @param uuid
     * @return  */
    public String getFirstName(LLUUID uuid) {
        if (uuid.equals(new LLUUID())) { return "NOUUID"; }
        if (Global.firstName(uuid)==null) { try { getCAPS().getNames(uuid); } catch (IOException e) { warn("Failed to lookup agent names",e); } }
        if (Global.firstName(uuid)==null) { Global.firstName(uuid,"???"); }
        return Global.firstName(uuid);
    }
    /** Resolve a UUID into lastname, either via cache or via lookup
     * @param uuid
     * @return  */
    public String getLastName(LLUUID uuid) {
        if (uuid.equals(new LLUUID())) { return "NOUUID"; }
        if (Global.lastName(uuid)==null) { try { getCAPS().getNames(uuid); } catch (IOException e) { warn("Failed to lookup agent names",e); } }
        if (Global.lastName(uuid)==null) { Global.lastName(uuid,"???"); }
        return Global.lastName(uuid);
    }
    /** Resolve a UUID into a username, either via cache or via lookup
     * @param uuid
     * @return  */
    public String getUserName(LLUUID uuid) {
        if (uuid.equals(new LLUUID())) { return "NOUUID"; }
        if (Global.userName(uuid)==null) { try { getCAPS().getNames(uuid); } catch (IOException e) { warn("Failed to lookup agent names",e); } }
        if (Global.userName(uuid)==null) { Global.userName(uuid,"???"); }
        return Global.userName(uuid);
    }
    /** Resolve a UUID into a displayname, either via cache or via lookup
     * @param uuid
     * @return  */
    public String getDisplayName(LLUUID uuid) {
        if (uuid.equals(new LLUUID())) { return "NOUUID"; }
        if (Global.displayName(uuid)==null) { try { getCAPS().getNames(uuid); } catch (IOException e) { warn("Failed to lookup agent names",e); } }
        if (Global.displayName(uuid)==null) { Global.displayName(uuid,"???"); }
        return Global.displayName(uuid);
    }


    private final Map<Long,Circuit> circuits=new HashMap<>();

    /** Obtain a circuit to the target.
     * If a live circuit already exists for this handle, that is returned, otherwise a new circuit is created and started.
     * Note the target sim must be expecting us.
     * @param numericip Sim IP address
     * @param port Sim IP port
     * @param handle Region handle
     * @param capsurl CAPS url for target region, potentially null for child agents which get this later.
     * @return Activated circuit for requested region handle
     * @throws IOException 
     */
    public Circuit createCircuit(String numericip, int port, long handle, String capsurl) throws IOException {
        synchronized(circuits) {
            if (circuits.containsKey(handle)) {
                if (circuits.get(handle).isAlive()) {
                    // already got a circuit
                    if (Debug.CIRCUIT) { debug("Duplicate circuit to "+handle+" ignored"); }
                    return circuits.get(handle);
                }
            }
            if (Debug.CIRCUIT) { debug("New circuit to "+handle); }
            Circuit newcircuit=new Circuit(this, numericip, port, handle, capsurl);
            newcircuit.connect();
            circuits.put(handle,newcircuit);
            return newcircuit;
        }
    }

    /** Inform bot that a circuit closed.
     * If this is our primary (non child agent) circuit we're in trouble and will quit.
     * @param regionhandle Region handle that's closing connection.
     * @param circ Associated circuit, used as a 'check' only
     */
    void deregisterCircuit(Long regionhandle, Circuit circ) {
        synchronized(circuits) {
            Circuit c=circuits.get(regionhandle);
            if (c!=null) { c.close(); }
            if (circ!=c && c!=null) { error("Closing a region handle but the circuit is not the one we have registered"); }
            circuits.remove(regionhandle);
            // dont warn if shutting down
            if (!quit && c==primary) { crit("Closure of primary circuit detected, this is fatal?"); shutdown("Primary circuit lost, we have been disconnected?"); }
        }
    }

    /** Get the regional info for the primary region
     * @return  */
    public Regional getRegional() {
        return primary.regional();
    }

    /** Get regional data for all connected circuit
     * @return  */
    public Set<Regional> getRegionals() {
        Set<Regional> regionalset=new HashSet<>();
        synchronized(circuits) {
            for (Long handle:circuits.keySet()) {
                //System.out.println("Circuit "+handle);
                regionalset.add(circuits.get(handle).regional());
            }
        }
        return regionalset;
    }

    /** Get the circuit for a given region handle */
    Circuit getCircuit(Long regionhandle) {
        return circuits.get(regionhandle); 
    }

    /** Get all circuits
     * @return  */
    public Collection<Circuit> getCircuits() {
        synchronized(circuits) {
            Set<Circuit> ret=new HashSet<>();
            ret.addAll(circuits.values());
            return ret;
        }
    }

    /** Initiate disconnection from SL
     * @param reason */
    public void shutdown(String reason) {
        if (quit) { return; } // do not re-enter
        quit=true; quitreason=reason;
        warn("Shutdown requested: "+reason);
        Set<Circuit> closeme=new HashSet<>();
        for (Circuit c:getCircuits()) {
            closeme.add(c); // because we'll get concurrent modification exceptions otherwise, as we close the circuits while iterating.
        }
        for (Circuit c:closeme) {
            try { c.close(); } catch (Exception e) {}
        }
        brain.stopProcrastinating();  // release the main thread
    }
    private float x=0; private float y=0; private float z=0;
    public LLVector3 getPos() { return new LLVector3(x,y,z); }
    public void setPos(float x, float y, float z) { this.x=x; this.y=y; this.z=z; }

    public void setPos(LLVector3 p) { x=p.x; y=p.y; z=p.z; }

    private float lx=0; private float ly=0; private float lz=0;
    public void setLookAt(LLVector3 l) {lx=l.x;ly=l.y;lz=l.z;}

    public LLVector3 getLookAt() { return new LLVector3(lx,ly,lz); }
    public void setLookAt(float x,float y,float z) { lx=x; ly=y; lz=z; }

    public void setFOV(float angle) throws IOException {
        AgentFOV fov=new AgentFOV();
        fov.bagentdata.vagentid=getUUID();
        fov.bagentdata.vcircuitcode=new U32(getCircuitCode());
        fov.bagentdata.vsessionid=getSession();
        fov.bfovblock.vgencounter=new U32(0);
        fov.bfovblock.vverticalangle=new F32(angle);
        send(fov,true);
    }
    public void setMaxFOV() throws IOException { setFOV((float) (Math.PI)); }
    public void setMinFOV() throws IOException { setFOV((float) 0.01); }
    public void drawDistance(float newdd) throws IOException {
        drawdistance=newdd;
        forceAgentUpdate();
    }


    private int circuitsequence=0;
    private final Object circuitsequencelock=new Object();
    int getCircuitSequence() { synchronized(circuitsequencelock) { circuitsequence++; return circuitsequence; } }

    @Override
    public String toString() { return this.getFullName(); }
    private void debug(String message) { debug(message,null); }
    private void debug(String message, Throwable t) { Log.log(this,Log.DEBUG,message,t); }
    private void info(String message) { info(message,null); }
    private void info(String message, Throwable t) { Log.log(this,Log.INFO,message,t); }
    private void note(String message) { note(message,null); }
    private void note(String message, Throwable t) { Log.log(this,Log.NOTE,message,t); }
    private void warn(String message) { warn(message,null); }
    private void warn(String message, Throwable t) { Log.log(this,Log.WARNING,message,t); }
    private void error(String message) { error(message,null); }
    private void error(String message, Throwable t) { Log.log(this,Log.ERROR,message,t); }
    private void crit(String message) { crit(message,null); }
    private void crit(String message, Throwable t) { Log.log(this,Log.CRITICAL,message,t); }    


}
