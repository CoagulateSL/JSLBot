package net.coagulate.JSLBot;
import java.io.ByteArrayInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.*;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import javax.xml.parsers.ParserConfigurationException;
import net.coagulate.JSLBot.Handlers.Regions;
import static net.coagulate.JSLBot.Log.*;
import net.coagulate.JSLBot.Packets.Messages.*;
import net.coagulate.JSLBot.Packets.Packet;
import net.coagulate.JSLBot.Packets.Types.*;
import static net.coagulate.JSLBot.Log.log;
import net.coagulate.JSLBot.Packets.Message;
import org.xml.sax.SAXException;

/** Creates and runs a JSLBot.
 *
 * @author Iain Price <git@predestined.net>
 */
public class JSLBot extends Thread {

    public static final int CIRCUIT_TIMEOUT=30; // seconds before we consider a circuit dead.
    private static final int MAX_RETRIES=10; // login attempts
    private static final long RETRY_INTERVAL=2500; // milliseconds between login attempts
    private long RETRY=2500; // doubled every attempt
    private static final long MAX_RETRY_INTERVAL=5000; // maximum retry interval
    public LLUUID getSession() { return sessionid; }

    public LLUUID getUUID() { return uuid; }

    public int getCC() { return circuitcode; }

    /* A word on threading in JSL Bot.
    * We use at least 2 threads, there is the main JSLBot AI thread (this class) and 
    * the Circuit driver threads.  The circuit driver is responsible for handling packets
    * ACKs and retransmissions, and it must not be blocked for extensive periods.
    * It hands off packets it receives, beyond those relevant to circuit management, 
    * to the reciver call in JSLBot which makes a decision - those that can be immediately
    * processed (data updates, etc) are run inside the Circuit driver thread.
    *
    * Events that might require more complex processing, such as receiving an IM
    * which is probably a method of making the bot do arbitary things by command
    * need to be decoupled from this thread, and are stacked on the event queue.
    *
    * External applications can also stack events on the event queue here, and then
    * these are executed serially by the main thread (AI thread), which can then do
    * slow things that require the circuit to be alive, such as teleport, lookup   
    * data from the sim (and then stall until the reply is sent, by the other thread)
    *
    * etc etc, yadda yadda, this seems to be a fairly normal design
    */
    
    public boolean quit=false;
    private String quitreason="";
    private static boolean initialised=false; // yes static, as in we've reconfigured the SSL engine

    // bot level data
    private String firstname;
    private String lastname;
    private String password; // eww
    private String loginlocation;
    private  LLUUID sessionid;
    private LLUUID uuid;
    public LLUUID master=null;  
    private int circuitcode;
    private Set<Handler> brain=new HashSet<Handler>(); public Set<Handler> getBrain() { return brain; }
    Configuration config=new TransientConfiguration();
    
    
    // create a bot
    public JSLBot(Configuration conf) throws Exception {
        config=conf;
        String location=config.get("loginlocation");
        if (location==null || location.equals("")) { location="home"; }  // default to home
        String potentialmaster=config.get("owner");
        if (potentialmaster!=null && (!(potentialmaster.equals("")))) { master=new LLUUID(potentialmaster); }
        Set<Handler> newbrain=new HashSet<Handler>();
        String handlerlist=config.get("handlers","");
        for (String name:handlerlist.split(",")) {
            try {
                Handler h=createHandler(name);
                newbrain.add(h);
            }
            catch (Exception e) {
                log(this,ERROR,"Handler "+name+" failed to initialise: "+e.toString(),e);
            }
        }
        setup(newbrain,config.get("firstname"),config.get("lastname"),config.get("password"),location);
        log(this,NOTE,"JSLBot initialisation complete, ready to launch");
    }
    
    /** Create a handler by class name
     * 
     * @param name
     * @return
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException 
     */
    private Handler createHandler(String name) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        String classname=name;
        if (!classname.contains(".")) { classname="net.coagulate.JSLBot.Handlers."+name; }
        Class c=Class.forName(classname);
        Configuration configuration=config.subspace(name);
        Constructor cons=c.getConstructor(Configuration.class);
        return (Handler) (cons.newInstance(configuration));
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
    // alternative convenience instansiators
    public JSLBot(String firstname,String lastname,String password) throws Exception {
        setup(null,firstname,lastname,password,"home");
    }
    public JSLBot(String firstname,String lastname,String password,String loginlocation) throws Exception {
        setup(null,firstname,lastname,password,loginlocation);
    }
    public JSLBot(Set<Handler> brain,String firstname,String lastname,String password) throws Exception {
        setup(brain,firstname,lastname,password,"home");
    }
    public JSLBot(Set<Handler> brain,String firstname,String lastname,String password,String loginlocation) throws Exception {
        setup(brain,firstname,lastname,password,loginlocation);
    }
    private void setup(Set<Handler> newbrain,String firstname,String lastname,String password,String loginlocation) throws CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        if (!initialised) { new LLCATruster(); initialised=true; }
        this.brain=newbrain;
        this.firstname=firstname;
        this.lastname=lastname;
        this.password=password;
        this.loginlocation=loginlocation;  
        if (brain==null) { brain=new HashSet<>(); }
        // nominated for 'best line of code, 2016'
        if (brain.isEmpty()) { log(this,WARNING,"Bot has no brain and will be a virtual zombie."); }
    }
    
    // you can run this with Thread.start() if you want to run lots of bots
    // if you just want to run this one bot, you can yield your main thread into here by calling JSLBot.run();
    public void run() {
        log(this,NOTE,"JSLBot launching connection...");        
        setName("JSLBot Brain for "+firstname+" "+lastname);
        Runtime.getRuntime().addShutdownHook(new ShutdownHook(this));
        try {
            mainLoop();
        }
        catch (Exception e)
        {
            Log.log(this,Log.ERROR,"Main bot loop crashed - "+e.toString(),e);
        }
    }
    
    public class ShutdownHook extends Thread {
        JSLBot bot;
        ShutdownHook(JSLBot bot) {this.bot=bot;}
        public void run() {bot.shutdown("JVM called shutdown hook (program terminated?)");}
    }
    
    private void performLogin(String firstname,String lastname,String password,String location) throws Exception {
        Exception last=null;
        for (int retries=0;retries<MAX_RETRIES;retries++) {
            try {
                login(firstname,lastname,password,location);
                // didn't exception :P
                return;
            }
            catch (Exception e) {
                last=e;
                RETRY=RETRY_INTERVAL*retries; if (RETRY>MAX_RETRY_INTERVAL) { RETRY=MAX_RETRY_INTERVAL; }
                note(this,"Login attempt "+(retries+1)+"/"+MAX_RETRIES+" failed: "+e.getMessage());
                try { Thread.sleep(RETRY); } catch (InterruptedException f) {}
            }
        }
        crit(this,"All login attempts failed!");
        throw new IOException("Failed login",last);
    }
    
    public String getFullName() { return firstname+" "+lastname; }
    public String getUsername() { return firstname+"."+lastname; }
    
    private void login(String firstname,String lastname,String password,String loginlocation) throws MalformedURLException, IOException, NoSuchAlgorithmException, XmlRpcException  {
        // howto SL login
        Thread.currentThread().setName("JSLBot controller for "+firstname+" "+lastname);
        XmlRpcClientConfigImpl config=new XmlRpcClientConfigImpl();
        config.setServerURL(new URL("https://login.agni.lindenlab.com/cgi-bin/login.cgi"));
        XmlRpcClient client=new XmlRpcClient();
        client.setConfig(config);
        HashMap params=new HashMap();
        this.firstname=firstname;
        this.lastname=lastname;
        params.put("first",firstname);
        params.put("last",lastname);
        params.put("start",loginlocation);
        params.put("channel","JSLBot <Iain Maltz@Second Life>");
        params.put("platform","Lin");
        String mac=BotUtils.getMac();
        if (mac==null) { Log.log(this,Log.ERROR,"Failed to appropriate MAC address"); } else { params.put("mac",mac); }
        // MD-5 =)
        params.put("passwd",BotUtils.md5hash(password));
        HashMap result=(HashMap)(client.execute("login_to_simulator",new Object[]{params}));
        if (Debug.AUTH) {
            // dump the result
            for(Object s:result.keySet()) {
                String printline=(((String)s)+" -> ");
                Object output=result.get(s);
                if (output instanceof String) { printline+=("[String] "+(String)output); }
                else {
                    if (output instanceof Integer) { printline+=("[Integer] "+(Integer)output); }
                    else {
                        printline+=(output);
                    }
                }
                Log.log(this,Log.DEBUG,printline);
            }
        }
        if (!(((String)result.get("login")).equalsIgnoreCase("true"))) {
            throw new IOException("Server gave error: "+((String)result.get("message")));
        }
        
        // well then, lets capture some useful information from the response, overwrite some in fact
        //String securesessionid=(String) result.get("secure_session_id"); // seems to be useless (?)
        // scraping SL upper/lower case.  note the firstname is quoted, the lastname isn't :)
        String firstnamestaging=((String)result.get("first_name"));
        this.firstname=firstnamestaging.substring(1,firstnamestaging.length()-1);
        this.lastname=(String)result.get("last_name");
        // very useful
        uuid=new LLUUID((String)result.get("agent_id"));
        // probably want to note the "udp_blacklist" which is a comma separated list of packet types to not use.  but then if we just aren't using them either...?
        // seed_capability necessary at this level of ops?
        // login MOTD
        String message=(String)result.get("message");
        // used for primary connection
        circuitcode=(int)result.get("circuit_code");
        String seedcaps=(String)result.get("seed_capability");
        // where we're connecting to
        String ip=(String)result.get("sim_ip");
        int port=(Integer)result.get("sim_port");
        int x=(Integer)result.get("region_x");
        int y=(Integer)result.get("region_y");
        U64 handle=new U64();
        handle.value=x;
        handle.value=handle.value<<(32);
        handle.value=handle.value | (y);     
        if (Debug.AUTH || Debug.REGIONHANDLES) { debug(this,"Computed initial handle of "+Long.toUnsignedString(handle.value)); }
        // important thing, per login? used in almost all packets it seems.
        sessionid=new LLUUID((String)result.get("session_id"));
        Log.log(this,Log.INFO,"Login is complete, opening initial circuit.");
        Circuit initial=new Circuit(this, ip, port, handle.value,seedcaps);
        // for our main connection, "move in" to the sim, it's expecting us :P
        primary=initial;
        circuits.put(handle.value, initial);
        completeAgentMovement();
        agentUpdate();
    }

    public void completeAgentMovement() throws IOException {
        CompleteAgentMovement p=new CompleteAgentMovement();
        p.bagentdata.vagentid=getUUID();
        p.bagentdata.vsessionid=getSession();
        p.bagentdata.vcircuitcode=new U32(getCC());
        send(p);
    }
    public void agentUpdate() throws IOException {
        AgentUpdate p=new AgentUpdate();
        p.bagentdata.vagentid=getUUID();
        p.bagentdata.vsessionid=getSession();
        Packet agentupdate=new Packet(p);
        agentupdate.setReliable(true);
        send(agentupdate);
    }


    public void send(Message m,boolean reliable) throws IOException {
        Packet p=new Packet(m);
        p.setReliable(reliable);
        send(p);
    }
    public void send(Message m) throws IOException {send(m,false); }
    Circuit primary=null;  public void send(Packet p) throws IOException { primary.send(p); }
    Circuit getPrimaryCircuit() { return primary; }
    public void setPrimaryCircuit(Circuit c) { primary=c; }
    
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
    
    // AI section... handle received packets, queue them, main thread runs them, yadda yadda
    
    Map <String,List<Handler>> immediatehandlers=new HashMap<>();
    Map <String,List<Handler>> delayedhandlers=new HashMap<>();
    Map <String,Handler> commands=new HashMap<>(); public Map<String,Handler> getCommands() {return commands;}
    final List <Event> queue=new ArrayList<>();
    
    public String execute(String command,Map<String,String> parameters) throws Exception
    {
        if (!commands.containsKey(command)) { return "No such command '"+command+"'"; }
        Handler h=commands.get(command);
        return h.execute(command,parameters);
    }
    
    // should be synchronised if this is ever called outside startup 
    public void addImmediateHandler(String type,Handler handler) {
        List<Handler> handlers=new ArrayList<Handler>();
        if (immediatehandlers.containsKey(type)) { handlers=immediatehandlers.get(type); }
        handlers.add(handler);
        immediatehandlers.put(type, handlers);
    }
    public void addHandler(String type,Handler handler) {
        List<Handler> handlers=new ArrayList<Handler>();
        if (delayedhandlers.containsKey(type)) { handlers=delayedhandlers.get(type); }
        handlers.add(handler);
        delayedhandlers.put(type, handlers);
    }
    public void addCommand(String command,Handler handler) {
        if (commands.containsKey(command)) { throw new IllegalArgumentException("Command "+command+" is already registered to "+commands.get(command)); }
        commands.put(command, handler);
    }
            
    
    // handle received packets
    // ===== WARNING WARNING WARNING =====
    // this method is called within the Circuit driver thread.  
    // dont do anything intensive OR ANYTHING THAT REQUIRES A SIMULATOR RESPONSE, because this thread needs to be released for those things to happen
    // ===== WARNING WARNING WARNING =====
    
    void process(Event event) throws IOException {
        String messageid=event.getName();
        if (immediatehandlers.containsKey(messageid)) {
            List<Handler> handlers=immediatehandlers.get(messageid);
            for (Handler h:handlers) {
                try {
                    h.processImmediate(event);
                }
                catch (Exception e) {
                    log(this,ERROR,"IMMEDIATE MODE handler "+h+" crashed with exception "+e.toString(),e);
                }
            }
        }
        synchronized(queue) { queue.add(event); queue.notifyAll(); }
    }
    private Set<String> unhandled=new HashSet<>();
    public void clearUnhandled() { unhandled=new HashSet<>(); } // useful for debugging when you want to see 'what happens next'
    void distribute(Event event) throws IOException {
        String messageid=event.getName();
        if (!(delayedhandlers.containsKey(messageid) || immediatehandlers.containsKey(messageid))) {
            if (Debug.UNHANDLEDALL) { log(this,Log.DEBUG,"Unhandled packet was "+event.dump()); }
            if (unhandled.contains(messageid)) { return; }
            log(this,NOTE,"Unhandled packet type "+messageid);
            if (Debug.UNHANDLEDONCE) { log(this,Log.DEBUG,"Unhandled packet was "+event.dump()); }
            unhandled.add(messageid);
            return;
        }

        if (!delayedhandlers.containsKey(messageid)) { return; }
        List<Handler> handlers=delayedhandlers.get(messageid);
        for (Handler h:handlers) {
            try {
                h.process(event);
            }
            catch (Exception e) {
                log(this,ERROR,"Handler "+h+" crashed with exception "+e.toString(),e);
            }
        }
    }
    void initialiseHandlers() throws Exception {
        // i feel like there is a joke in here somewhere
        for (Handler h:brain) {
            h.initialise(this);
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
                    catch (Exception ex) { error(this,"Event distributor crashed, continuing, but that event is imcompletely handled"+e.getName(),ex); }
                } else { Log.log(this,WARN,"Unknown class on event queue"+event.getClass().getName()); }
            }

        }
        Log.log(this,Log.NOTE,"Bot exited:"+quitreason);
    }

    private Map<Long,Circuit> circuits=new HashMap<>();

    void dropCircuit(Circuit circuit) {
        if (circuit==primary) { quit=true; quitreason="Simulator notified us the primary circuit was closed";}
        while (circuits.containsValue(circuit)) {
            synchronized(circuits) {
                for (Long scan:circuits.keySet()) {
                    if (circuits.get(scan)==circuit) { circuits.remove(scan); break; }
                }
            }
        }
    }

    public CAPS getCAPS() { return primary.getCAPS(); }
    public String getFirstName(LLUUID uuid) {
        if (uuid.equals(new LLUUID())) { return "NOUUID"; }
        if (Global.getFirstName(uuid)==null) { try { getCAPS().getNames(uuid); } catch (IOException e) { log(this,WARN,"Failed to lookup agent names",e); } }
        if (Global.getFirstName(uuid)==null) { Global.setFirstName(uuid,"???"); }
        return Global.getFirstName(uuid);
    }
    public String getLastName(LLUUID uuid) {
        if (uuid.equals(new LLUUID())) { return "NOUUID"; }
        if (Global.getLastName(uuid)==null) { try { getCAPS().getNames(uuid); } catch (IOException e) { log(this,WARN,"Failed to lookup agent names",e); } }
        if (Global.getFirstName(uuid)==null) { Global.setLastName(uuid,"???"); }
        return Global.getLastName(uuid);
    }
    public String getUserName(LLUUID uuid) {
        if (uuid.equals(new LLUUID())) { return "NOUUID"; }
        if (Global.getUserName(uuid)==null) { try { getCAPS().getNames(uuid); } catch (IOException e) { log(this,WARN,"Failed to lookup agent names",e); } }
        if (Global.getFirstName(uuid)==null) { Global.setUserName(uuid,"???"); }
        return Global.getUserName(uuid);
    }
    public String getDisplayName(LLUUID uuid) {
        if (uuid.equals(new LLUUID())) { return "NOUUID"; }
        if (Global.getDisplayName(uuid)==null) { try { getCAPS().getNames(uuid); } catch (IOException e) { log(this,WARN,"Failed to lookup agent names",e); } }
        if (Global.getFirstName(uuid)==null) { Global.setDisplayName(uuid,"???"); }
        return Global.getDisplayName(uuid);
    }

    public Handler register(String handlername) throws Exception {
        if (handlername==null) { throw new NullPointerException("No CLASS parameter specified"); }
        Handler h=createHandler(handlername);
        h.initialise(this);
        getBrain().add(h);
        h.loggedIn();
        String newvalue=config.getMaster().get("handlers","");
        if (!newvalue.equals("")) { newvalue+=","; }
        newvalue+=handlername;
        config.getMaster().put("handlers",newvalue);
        return h;
    }

    Packet useCircuitCode() {
        UseCircuitCode cc=new UseCircuitCode();
        cc.bcircuitcode.vcode=new U32(getCC());
        cc.bcircuitcode.vsessionid=getSession();
        cc.bcircuitcode.vid=getUUID();
        Packet p=new Packet(cc);
        p.setReliable(true);
        return p;
    }

    public Circuit createCircuit(String numericip, int port, Long handle, String capsurl) throws IOException {
        synchronized(circuits) {
            if (circuits.containsKey(handle)) {
                if (circuits.get(handle).isAlive()) {
                    // already got a circuit
                    if (Debug.CIRCUIT) { debug(this,"Duplicate circuit to "+handle+" ignored"); }
                    return circuits.get(handle);
                }
            }
            if (Debug.CIRCUIT) { debug(this,"New circuit to "+handle); }
            Circuit newcircuit=new Circuit(this, numericip, port, handle, capsurl);
            circuits.put(handle,newcircuit);
            return newcircuit;
        }
    }

    void deregisterCircuit(Long regionhandle, Circuit circ) {
        synchronized(circuits) {
            circuits.remove(regionhandle);
            // dont warn if shutting down
            if (!quit && circ==primary) { crit(this,"Closure of primary circuit detected, this is fatal?"); shutdown("Primary circuit lost, we have been disconnected?"); }
        }
    }

    public Regional getRegional() {
        return primary.getRegional();
    }

    public Set<Regional> getRegionals() {
        Set<Regional> regionalset=new HashSet<>();
        synchronized(circuits) {
            for (Long handle:circuits.keySet()) {
                //System.out.println("Circuit "+handle);
                regionalset.add(circuits.get(handle).getRegional());
            }
        }
        return regionalset;
    }

    Circuit getCircuit(Long regionhandle) {
        return circuits.get(regionhandle); 
    }

    public Collection<Circuit> getCircuits() {
        return circuits.values();
    }

    public void shutdown(String reason) {
        if (quit) { return; } // do not re-enter
        quit=true; quitreason=reason;
        warn(this,"Shutdown requested: "+reason);
        Set<Circuit> closeme=new HashSet<>();
        for (Circuit c:getCircuits()) {
            closeme.add(c); // because we'll get concurrent modification exceptions otherwise, as we close the circuits while iterating.
        }
        for (Circuit c:closeme) {
            try { c.close(); } catch (Exception e) {}
        }
        // 0 - requested exit
        // 1 - Primary circuit closed (teleport failed, usually)
    }
    private float x=0; private float y=0; private float z=0;
    public LLVector3 getPos() { return new LLVector3(x,y,z); }
    public void setPos(float x, float y, float z) { this.x=x; this.y=y; this.z=z; }

    public void setPos(LLVector3 p) { x=p.x; y=p.y; z=p.z; }

    private float lx=0; private float ly=0; private float lz=0;
    public void setLookAt(LLVector3 l) {lx=l.x;ly=l.y;lz=l.z;}
    public Circuit circuit() { return this.primary; }

    public LLVector3 getLookAt() { return new LLVector3(lx,ly,lz); }
    public void setLookAt(float x,float y,float z) { lx=x; ly=y; lz=z; }
}
