package net.coagulate.JSLBot;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.*;
import static net.coagulate.JSLBot.Event.EVENTTYPE.*;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Messages.*;
import net.coagulate.JSLBot.Packets.Packet;
import net.coagulate.JSLBot.Packets.Types.*;
import org.apache.xmlrpc.XmlRpcException;

/** Creates and runs a JSLBot.
 *
 * @author Iain Price <git@predestined.net>
 */
public class JSLBot extends Thread {
    // bot level data
    private String firstname;
    private String lastname;
    public String getFullName() { return firstname+" "+lastname; }
    public String getUsername() { return firstname+"."+lastname; }
    private String password;
    private String loginlocation;
    private LLUUID sessionid; public LLUUID getSession() { return sessionid; }
    private LLUUID uuid; public LLUUID getUUID() { return uuid; }
    private int circuitcode; public int getCC() { return circuitcode; }
    
    private Set<Handler> brain=new HashSet<Handler>(); public Set<Handler> getBrain() { return brain; }
    Configuration config=new TransientConfiguration();

    private boolean quit=false; public boolean quitting() { return quit; }
    private String quitreason="";
    
    JSLInterface jslinterface; public JSLInterface api() { return jslinterface; }
    
    // create a bot from a configuration store
    public JSLBot(Configuration conf) throws Exception {
        // load from config and call 'setup'
        config=conf;
        String location=config.get("loginlocation");
        if (location==null || location.equals("")) { location="home"; }  // default to home
        //String potentialmaster=config.get("owner");
        Set<Handler> newbrain=new HashSet<Handler>();
        String handlerlist=config.get("handlers","");
        for (String name:handlerlist.split(",")) {
            try { newbrain.add(createHandler(name)); }
            catch (Exception e) { error("Handler "+name+" failed to initialise: "+e.toString(),e); }
        }
        setup(newbrain,config.get("firstname"),config.get("lastname"),config.get("password"),location);
        note("JSLBot initialisation complete, ready to launch");
    }
    // alternative convenience instansiators
    public JSLBot(String firstname,String lastname,String password) throws Exception { setup(null,firstname,lastname,password,"home"); }
    public JSLBot(String firstname,String lastname,String password,String loginlocation) throws Exception { setup(null,firstname,lastname,password,loginlocation); }
    public JSLBot(Set<Handler> brain,String firstname,String lastname,String password) throws Exception { setup(brain,firstname,lastname,password,"home"); }
    public JSLBot(Set<Handler> brain,String firstname,String lastname,String password,String loginlocation) throws Exception { setup(brain,firstname,lastname,password,loginlocation); }

    
    
    
    
    //********** HANDLERS **********
    private Handler createHandler(String name) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        // load "Handler" class with default package and a subspace of configuration
        String classname=name;
        if (!classname.contains(".")) { classname="net.coagulate.JSLBot.Handlers."+name; }
        Class c=Class.forName(classname);
        Configuration configuration=config.subspace(name);
        Constructor cons=c.getConstructor(JSLBot.class,Configuration.class);
        return (Handler) (cons.newInstance(this,configuration));
    }
    public Handler getHandler(String name)
    {
        Handler h=getHandlerOrNull(name);
        if (h==null) { throw new IllegalArgumentException("Module "+name+" not found or not loaded"); }
        return h;
    }
    public Handler getHandlerOrNull(String name)
    {
        for (Handler h:getBrain()) {
            if (h.getClass().getSimpleName().equalsIgnoreCase(name)) {
                return h;
            }
        }
        return null;
    }
    
    
    
    
    
    
    // ********** BOT STARTUP **********
    private boolean connected=false; public boolean connected() { return connected; }
    private Object connectsignal=new Object();
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
    
    private void setup(Set<Handler> newbrain,String firstname,String lastname,String password,String loginlocation) throws CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException {        
        jslinterface=new JSLInterface(this);
        LLCATruster.initialise(); // probably compromises the SSL engine in various ways :(
        this.brain=newbrain;
        this.firstname=firstname;
        this.lastname=lastname;
        this.password=password;
        this.loginlocation=loginlocation;  
        if (brain==null) { brain=new HashSet<>(); }
        // nominated for 'best line of code, 2016'
        // ^^ nominated for most useul comment for dating my intermittent work on this project, now mid 2018.
        if (brain.isEmpty()) { warn("Bot has no brain and will be a virtual zombie."); }
    }
    
    /** Launch bot AI.
     * you can run this with Thread.start() if you want to run lots of bots
     * if you just want to run this one bot, you can yield your main thread into here by calling JSLBot.run();
     */
    public void run() {
        note("JSLBot launching connection...");        
        setName("JSLBot Brain for "+firstname+" "+lastname);
        Runtime.getRuntime().addShutdownHook(new ShutdownHook(this));
        // catch and report.  "mainLoop()" should guard against everything its self so this means that function is broken
        try { mainLoop(); }
        catch (Exception e) { error("Main bot loop crashed - "+e.toString(),e); }
    }

    private class ShutdownHook extends Thread {
        JSLBot bot;
        ShutdownHook(JSLBot bot) {this.bot=bot;}
        public void run() {bot.shutdown("JVM called shutdown hook (program terminated?)");}
    }
    
    /** Wrapper for logging in, implements retries and backoff. */
    private void performLogin(String firstname,String lastname,String password,String location) throws Exception {
        Exception last=null;
        for (int retries=0;retries<Constants.MAX_RETRIES;retries++) {
            try { login(firstname,lastname,password,location); return; }
            catch (Exception e) {
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
        int x=(Integer)result.get("region_x");
        int y=(Integer)result.get("region_y");
        // derive region handle
        U64 handle=new U64();
        handle.value=x;
        handle.value=handle.value<<(32);
        handle.value=handle.value | (y);     
        if (Debug.AUTH || Debug.REGIONHANDLES) { debug("Computed initial handle of "+Long.toUnsignedString(handle.value)); }
        // caps
        String seedcaps=(String)result.get("seed_capability");
        info("Login is complete, opening initial circuit.");
        Circuit initial=new Circuit(this, ip, port, handle.value,seedcaps);
        // for our main connection, "move in" to the sim, it's expecting us :P
        primary=initial;
        circuits.put(handle.value, initial);
        completeAgentMovement();
        agentUpdate();
        connected=true;
        synchronized(connectsignal) { connectsignal.notifyAll(); } // wake up, sleepers
    }

    /** Send this generally useful message down the primary UDP circuit */
    public void completeAgentMovement() throws IOException {
        CompleteAgentMovement p=new CompleteAgentMovement();
        p.bagentdata.vagentid=getUUID();
        p.bagentdata.vsessionid=getSession();
        p.bagentdata.vcircuitcode=new U32(getCC());
        send(p);
    }
    /** Send this generally useful message down the primary UDP circuit */
    public void agentUpdate() throws IOException {
        AgentUpdate p=new AgentUpdate();
        p.bagentdata.vagentid=getUUID();
        p.bagentdata.vsessionid=getSession();
        Packet agentupdate=new Packet(p);
        agentupdate.setReliable(true);
        send(agentupdate);
    }

    /** Send this generally useful message down the primary UDP circuit */
    Packet useCircuitCode() {
        UseCircuitCode cc=new UseCircuitCode();
        cc.bcircuitcode.vcode=new U32(getCC());
        cc.bcircuitcode.vsessionid=getSession();
        cc.bcircuitcode.vid=getUUID();
        Packet p=new Packet(cc);
        p.setReliable(true);
        return p;
    }


    /** Send an instant message immediately using the primary circuit */
    public void im(LLUUID uuid,String message) throws IOException {
        ImprovedInstantMessage reply=new ImprovedInstantMessage();
        reply.bagentdata.vagentid=getUUID();
        reply.bagentdata.vsessionid=getSession();
        reply.bmessageblock.vtoagentid=uuid;
        reply.bmessageblock.vmessage=new Variable2(message);
        Packet replypacket=new Packet(reply);
        replypacket.setReliable(true);
        send(replypacket);
    }    

    
    
    // ********** TRANSMISSION PRIMITIVES **********
    /** Primary circuit, as in where the agent presence *is* */
    Circuit primary=null;  Circuit circuit() { return primary; }
    public String getRegionName() { return circuit().getRegionName(); }
    
    public void send(Packet p) throws IOException { primary.send(p); }
    public void send(Message m,boolean reliable) throws IOException {
        Packet p=new Packet(m);
        p.setReliable(reliable);
        send(p);
    }
    public void send(Message m) throws IOException {send(m,false); }
    public void setPrimaryCircuit(Circuit c) { primary=c; }
    
    






    // ********** HANDLERS **********
    // ***** Registration *****
    
    Map <String,List<Handler>> immediatehandlers=new HashMap<>();
    Map <String,List<Handler>> delayedhandlers=new HashMap<>();
    final List <Event> queue=new ArrayList<>();
    
    // should be synchronised if this is ever called outside startup 
    private void addImmediateHandler(String type,Handler handler) {
        List<Handler> handlers=new ArrayList<Handler>();
        if (immediatehandlers.containsKey(type)) { handlers=immediatehandlers.get(type); }
        handlers.add(handler);
        immediatehandlers.put(type, handlers);
    }
    private void addHandler(String type,Handler handler) {
        List<Handler> handlers=new ArrayList<Handler>();
        if (delayedhandlers.containsKey(type)) { handlers=delayedhandlers.get(type); }
        handlers.add(handler);
        delayedhandlers.put(type, handlers);
    }
    public void addUDP(String type,Handler handler) { addHandler("UDP/"+type,handler); }
    public void addImmediateUDP(String type,Handler handler) { addImmediateHandler("UDP/"+type,handler); }
    public void addXML(String type,Handler handler) { addHandler("XML/"+type,handler); }
    public void addImmediateXML(String type,Handler handler) { addImmediateHandler("XML/"+type,handler); }
    public void addCommand(String type,Handler handler) { addHandler("CMD/"+type,handler); addImmediateHandler("CMD/"+type,handler); }
            
    
    // handle received packets
    // ===== WARNING WARNING WARNING =====
    // this method is called within the Circuit driver thread.  
    // dont do anything intensive OR ANYTHING THAT REQUIRES A SIMULATOR RESPONSE, because this thread needs to be released for those things to happen
    // ===== WARNING WARNING WARNING =====
    private Set<String> unhandled=new HashSet<>();
    String submit(Event event) throws IOException { // the IMMEDIATE MODE code, called in the callers thread
        String messageid=event.getPrefixedName();
        String response=null;
        event.status(Event.STATUS.IMMEDIATE);
        if (immediatehandlers.containsKey(messageid)) {
            List<Handler> handlers=immediatehandlers.get(messageid);
            for (Handler h:handlers) {
                try {
                    // it's not that tempting to generalise with reflection, it'll just look ugly.
                    if (event.type()==UDP) { h.processImmediateUDP(event.region(), (UDPEvent) event,event.getName()); }
                    if (event.type()==XML) { h.processImmediateXML(event.region(), (XMLEvent) event,event.getName()); }
                    if (event.type()==COMMAND && ((CommandEvent)event).immediate()) {
                        if (Debug.TRACKCOMMANDS) { debug("Command "+((CommandEvent)event).getName()+" invoking immediate handler "+h.getClass().getSimpleName()); }
                        response=h.execute(event.region(), (CommandEvent) event,event.getName(),((CommandEvent)event).parameters());
                    }
                }
                catch (Exception e) { error("IMMEDIATE MODE handler "+h+" for event "+messageid+" crashed with exception "+e.toString(),e); }
            }
        }
        event.status(Event.STATUS.QUEUED);
        synchronized(queue) { queue.add(event); queue.notifyAll(); } // and queue for the delayed thread
        return response;
    }
    void distribute(Event event) throws IOException { // dequeued events in the delayed (bot AI) thread.
        String messageid=event.getPrefixedName();
        String response=null;
        event.status(Event.STATUS.RUNNING);
        // log unhandled messages, as a reminder of things to do if nothing else
        if (!(delayedhandlers.containsKey(messageid) || immediatehandlers.containsKey(messageid))) {
            if (Debug.UNHANDLEDALL) { debug("Unhandled packet was "+event.dump()); }
            if (unhandled.contains(messageid)) { return; }
            note("Unhandled packet type "+messageid);
            if (Debug.UNHANDLEDONCE) { debug("Unhandled packet was "+event.dump()); }
            unhandled.add(messageid);
            event.status(Event.STATUS.COMPLETE);            
            return;
        }
        // fast bail?
        if (delayedhandlers.containsKey(messageid)) {
            List<Handler> handlers=delayedhandlers.get(messageid);
            for (Handler h:handlers) {
                try {
                    if (event.type()==UDP) { h.processUDP(event.region(), (UDPEvent) event,event.getName()); }
                    if (event.type()==XML) { h.processXML(event.region(), (XMLEvent) event,event.getName()); }
                    if (event.type()==COMMAND && !((CommandEvent)event).immediate()) {
                        if (Debug.TRACKCOMMANDS) { debug("Command "+((CommandEvent)event).getName()+" invoking delayed handler "+h.getClass().getSimpleName()); }
                        response=h.execute(event.region(), (CommandEvent) event,event.getName(),((CommandEvent)event).parameters());
                        if (response!=null && !response.equals("") && ((CommandEvent)event).respondTo()!=null) {
                            im(((CommandEvent)event).respondTo(),"> "+response);
                            ((CommandEvent)event).respondTo(response);
                        }
                    }
                }
                catch (Exception e) { error("Handler (not-immediate) "+h+" for event "+messageid+" crashed with exception "+e.toString(),e); }
            }
        }
        event.status(Event.STATUS.COMPLETE);
    }
    
    void initialiseHandlers() throws Exception {
        // i feel like there is a joke in here somewhere
        for (Handler h:brain) {
            h.initialise();
        }
    }
    void notifyHandlers() throws Exception { for (Handler h:brain) { h.loggedIn(); }}
    
    public void mainLoop() throws Exception {
        initialiseHandlers();
        performLogin(firstname,lastname,password,loginlocation);
        notifyHandlers();
        while (!quit) {
            Event event=null;
            synchronized(queue) {
                while (queue.isEmpty()) {
                    queue.wait();
                }
                event=queue.remove(0);
            }
            if (event!=null) {
                if (event instanceof Event) {
                    Event e=(Event)event;
                    try { distribute(e); }
                    catch (Exception ex) { error("Event distributor crashed, continuing, but that event is imcompletely handled"+e.getName(),ex); }
                } else { warn("Unknown class on event queue"+event.getClass().getName()); }
            }

        }
        note("Bot exited:"+quitreason);
    }

    public CAPS getCAPS() { return primary.getCAPS(); }
    /** Resolve a UUID into a firstname, either via cache or via lookup */
    public String getFirstName(LLUUID uuid) {
        if (uuid.equals(new LLUUID())) { return "NOUUID"; }
        if (Global.firstName(uuid)==null) { try { getCAPS().getNames(uuid); } catch (IOException e) { warn("Failed to lookup agent names",e); } }
        if (Global.firstName(uuid)==null) { Global.firstName(uuid,"???"); }
        return Global.firstName(uuid);
    }
    /** Resolve a UUID into lastname, either via cache or via lookup */
    public String getLastName(LLUUID uuid) {
        if (uuid.equals(new LLUUID())) { return "NOUUID"; }
        if (Global.lastName(uuid)==null) { try { getCAPS().getNames(uuid); } catch (IOException e) { warn("Failed to lookup agent names",e); } }
        if (Global.lastName(uuid)==null) { Global.lastName(uuid,"???"); }
        return Global.lastName(uuid);
    }
    /** Resolve a UUID into a username, either via cache or via lookup */
    public String getUserName(LLUUID uuid) {
        if (uuid.equals(new LLUUID())) { return "NOUUID"; }
        if (Global.userName(uuid)==null) { try { getCAPS().getNames(uuid); } catch (IOException e) { warn("Failed to lookup agent names",e); } }
        if (Global.userName(uuid)==null) { Global.userName(uuid,"???"); }
        return Global.userName(uuid);
    }
    /** Resolve a UUID into a displayname, either via cache or via lookup */
    public String getDisplayName(LLUUID uuid) {
        if (uuid.equals(new LLUUID())) { return "NOUUID"; }
        if (Global.displayName(uuid)==null) { try { getCAPS().getNames(uuid); } catch (IOException e) { warn("Failed to lookup agent names",e); } }
        if (Global.displayName(uuid)==null) { Global.displayName(uuid,"???"); }
        return Global.displayName(uuid);
    }

    public Handler register(String handlername) throws Exception {
        if (handlername==null) { throw new NullPointerException("No CLASS parameter specified"); }
        Handler h=createHandler(handlername);
        h.initialise();
        getBrain().add(h);
        h.loggedIn();
        String newvalue=config.getMaster().get("handlers","");
        if (!newvalue.equals("")) { newvalue+=","; }
        newvalue+=handlername;
        config.getMaster().put("handlers",newvalue);
        return h;
    }
    private Map<Long,Circuit> circuits=new HashMap<>();

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

    /** Get the regional info for the primary region */
    public Regional getRegional() {
        return primary.regional();
    }

    /** Get regional data for all connected circuit */
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

    /** Get all circuits */
    public Collection<Circuit> getCircuits() {
        return circuits.values();
    }

    /** Initiate disconnection from SL */
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
        synchronized(queue) { queue.notifyAll(); } // wake up the thread to quit
    }
    private float x=0; private float y=0; private float z=0;
    public LLVector3 getPos() { return new LLVector3(x,y,z); }
    public void setPos(float x, float y, float z) { this.x=x; this.y=y; this.z=z; }

    public void setPos(LLVector3 p) { x=p.x; y=p.y; z=p.z; }

    private float lx=0; private float ly=0; private float lz=0;
    public void setLookAt(LLVector3 l) {lx=l.x;ly=l.y;lz=l.z;}

    public LLVector3 getLookAt() { return new LLVector3(lx,ly,lz); }
    public void setLookAt(float x,float y,float z) { lx=x; ly=y; lz=z; }








    public void debug(String message) { debug(message,null); }
    public void debug(String message, Throwable t) { Log.log(this,Log.DEBUG,message,t); }
    public void info(String message) { info(message,null); }
    public void info(String message, Throwable t) { Log.log(this,Log.INFO,message,t); }
    public void note(String message) { note(message,null); }
    public void note(String message, Throwable t) { Log.log(this,Log.NOTE,message,t); }
    public void warn(String message) { warn(message,null); }
    public void warn(String message, Throwable t) { Log.log(this,Log.WARNING,message,t); }
    public void error(String message) { error(message,null); }
    public void error(String message, Throwable t) { Log.log(this,Log.ERROR,message,t); }
    public void crit(String message) { crit(message,null); }
    public void crit(String message, Throwable t) { Log.log(this,Log.CRITICAL,message,t); }    


}
