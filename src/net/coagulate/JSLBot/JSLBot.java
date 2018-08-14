package net.coagulate.JSLBot;
import java.io.*;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.MalformedURLException;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.*;
import static net.coagulate.JSLBot.Event.EVENTTYPE.*;
import net.coagulate.JSLBot.Handlers.CnC;
import net.coagulate.JSLBot.JSLBot.CmdHelp;
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
    private LLUUID sessionid; public LLUUID getSession() { return sessionid; }
    private LLUUID uuid; public LLUUID getUUID() { return uuid; }
    private int circuitcode; public int getCC() { return circuitcode; }
    
    private Set<Handler> brain=new HashSet<Handler>(); public Set<Handler> getBrain() { return brain; }
    Configuration config=new TransientConfiguration();

    private boolean quit=false; public boolean quitting() { return quit; }
    private String quitreason="";
    private final boolean DEFAULT_RECONNECT=false;
    private boolean reconnect=false; public void setReconnect() { reconnect=true; }
    public void forceReconnect() { reconnect=true; shutdown("Forced to reconnect"); }
    public boolean ALWAYS_RECONNECT=false;
    JSLInterface jslinterface; public JSLInterface api() { return jslinterface; }
    
    // create a bot from a configuration store
    public JSLBot(Configuration conf) throws Exception {
        // load from config and call 'setup'
        config=conf;
        String location=config.get("loginlocation");
        if (location==null || location.equals("")) { location="home"; }  // default to home
        //String potentialmaster=config.get("owner");
        
        String handlerlist=config.get("handlers","");
        Set<String> handlernames=new HashSet<>();
        for (String h:handlerlist.split(",")) { handlernames.add(h); }
        Set<Handler> newbrain=createBrain(handlernames);
        setup(newbrain,config.get("firstname"),config.get("lastname"),config.get("password"),location);
        note("JSLBot initialisation complete, ready to launch");
    }
    // alternative convenience instansiators
    public JSLBot(String firstname,String lastname,String password) throws Exception { setup(null,firstname,lastname,password,"home"); }
    public JSLBot(String firstname,String lastname,String password,String loginlocation) throws Exception { setup(null,firstname,lastname,password,loginlocation); }
    public JSLBot(Set<String> handlers, String firstname,String lastname,String password) throws Exception {
        setup(createBrain(handlers),firstname,lastname,password,"home");
    }
    public JSLBot(Set<String> handlers,String firstname,String lastname,String password,String loginlocation) throws Exception {
        setup(createBrain(handlers),firstname,lastname,password,loginlocation);
    }

    private Set<Handler> createBrain(Set<String> handlers) {
        Set<Handler> newbrain=new HashSet<>();
        for (String name:handlers) {
            try { newbrain.add(createHandler(name)); }
            catch (Exception e) { error("Handler "+name+" failed to initialise: "+e.toString(),e); }
        }
        return newbrain;
    }
    
    
    
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
    
    private void setup(Set<Handler> newbrain,String firstname,String lastname,String password,String loginlocation) throws CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException, NoSuchMethodException {        
        // test that method names are preserved
        String argname=this.getClass().getDeclaredMethod("setup",Set.class,String.class,String.class,String.class,String.class).getParameters()[0].getName();
        if (argname.equals("arg0") || (!argname.equals("newbrain"))) {
            System.out.println("===== FATAL ERROR =====");
            System.out.println("The name of the first method for setup() is "+argname);
            System.out.println("In the source this is called 'newbrain'");
            System.out.println("JSLBot uses reflection to read parameter names to implement bot commands");
            System.out.println("It is necessary to include parameter names in the compiled bytecode");
            System.out.println("This is generally done by compiling (javac) with the '-g' and '-parameters' option to enable debug info");
            System.out.println("Command functionality will not work without this feature, the bot has been aborted for safety reasons");
            System.out.println("NetBeans note: Add to compiler additional options and DISABLE compile on save whic ignores these settings");
            throw new IllegalArgumentException("Invalid JSLBot compilation, expected argument name 'newbrain', got '"+argname+"'");
        }
        
        jslinterface=new JSLInterface(this);
        LLCATruster.initialise(); // probably compromises the SSL engine in various ways :(
        this.brain=newbrain;
        this.firstname=firstname;
        this.lastname=lastname;
        this.password=password;
        this.loginlocation=loginlocation;  
    }

    // track our launch attempts, ALWAYS_RECONNECT will only permit 5 attempts in 10 minutes...
    private Date[] launches=new Date[Constants.MAX_LAUNCH_ATTEMPTS];
    private void loginLoopSafety() {
        // if we have any null slots then we didn't even launch MAX times yet
        for (int i=0;i<launches.length;i++) {
            if (launches[i]==null) {
                info("Reconnection Safety: We have not yet launched 5 times");
                launches[i]=new Date(); return;// use slot, return OK
            } 
        }
        // not not any null slots, whats the oldest timer?
        Date oldest=null;
        for (Date d:launches) {
            if (oldest==null) { oldest=d; } 
            else { if (d.before(oldest)) { oldest=d; } }
        }
        long ago=new Date().getTime()-oldest.getTime();
        int secondsago=(int)ago;
        info("Reconnection Safety: Last 5 login attempts took place over "+secondsago+" seconds");
        if (ago<(Constants.MAX_LAUNCH_ATTEMPTS_WINDOW_SECONDS)) { 
            crit("Reconnection Safety: This is less than the threshold of "+Constants.MAX_LAUNCH_ATTEMPTS_WINDOW_SECONDS+", tripping safety.");
            loginLoopSafetyViolation();
            return; // if we get here.
        }
        // otherwise, overwrite oldest date with now and continue
        for (int i=0;i<launches.length;i++) {
            if (launches[i]==oldest) { launches[i]=new Date(); return; }
        }
        // should never get here
        throw new AssertionError("An oldest launch time was found in pass #1, but could not be found to be replaced in pass #2");
    }
    private void loginLoopSafetyViolation() {
        // probably need some choices here, sometimes it's probably appropriate to "exit" the class, perhaps via an 'error' of some kind that wont get caught
        // sometimes its probably appropriate to stop the whole system if the bot is critical (system.exit?)
        // sometimes the remainder of the application is more important and it should continue, and we should just sleep, which is what we do for now
        // no configuration here yet, hard coded 15 minute sleep, have fun with that.
        for (int i=15;i>0;i--) {
            crit("Reconnection Safety: RECONNECTION SAFETY HAS TRIPPED.  THREAD FORCE-SLEEPING FOR "+i+" MINUTES.");
            try { Thread.sleep(60000); } catch (InterruptedException e) {}
        }
        warn("Reconnection Safety: Reconnection safety tripped, we have slept for 15 minutes, and will now return to attempting connections.");
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
        if (brain==null) { brain=new HashSet<>(); }
        // nominated for 'best line of code, 2016'
        // ^^ nominated for most useul comment for dating my intermittent work on this project, now mid 2018.
        if (brain.isEmpty()) { warn("Bot has no brain and will be a virtual zombie."); }
        reconnect=true;
        for (int i=0;i<launches.length;i++) { launches[i]=null; }
        launches[0]=new Date();
        while (ALWAYS_RECONNECT || reconnect) {
            quit=false; quitreason=""; connected=false; primary=null;
            reconnect=DEFAULT_RECONNECT;
            immediatehandlers=new HashMap<>(); delayedhandlers=new HashMap<>(); circuits=new HashMap<>();
            try { mainLoop(); }
            catch (Exception e) { error("Main bot loop crashed - "+e.toString(),e); }
            loginLoopSafety();
        }
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
    private Date lastagentupdate=null;
    private float drawdistance=(float) 64.0;
    public void forceAgentUpdate() throws IOException { agentUpdate(true); }
    public void agentUpdate() throws IOException { agentUpdate(false); }
    /** Send this generally useful message down the primary UDP circuit */
    public void agentUpdate(boolean force) throws IOException {
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
        cc.bcircuitcode.vcode=new U32(getCC());
        cc.bcircuitcode.vsessionid=getSession();
        cc.bcircuitcode.vid=getUUID();
        Packet p=new Packet(cc);
        p.setReliable(true);
        return p;
    }


    /** Send an instant message immediately using the primary circuit */
    public void im(LLUUID uuid,String message) throws IOException {
        ImprovedInstantMessage reply=new ImprovedInstantMessage(this);
        reply.bmessageblock.vtoagentid=uuid;
        reply.bmessageblock.vmessage=new Variable2(message);
        send(reply,true);
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
    public void addCommand(String name,Handler handler) {
        if (handler.getClass()==CnC.class) {
            addHandler("CMD/"+name,handler); addImmediateHandler("CMD/"+name,handler);
        } else {
            String lcname=handler.getClass().getSimpleName()+"."+name;
            lcname=lcname.toLowerCase();
            addHandler("CMD/"+lcname,handler); addImmediateHandler("CMD/"+lcname,handler);
        }
    }
            
    
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
                        String commandname=((CommandEvent)event).getName();                        
                        if (Debug.TRACKCOMMANDS) { debug("Command "+commandname+" invoking immediate handler "+h.getClass().getSimpleName()); }
                        response=executeCommand(commandname,(CommandEvent)event,h);
                        if (response!=null && !response.isEmpty()) { ((CommandEvent)event).response(response); }
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
        //System.out.println(messageid);
        String response=null;
        event.status(Event.STATUS.RUNNING);
        // log unhandled messages, as a reminder of things to do if nothing else
        if (!(delayedhandlers.containsKey(messageid) || immediatehandlers.containsKey(messageid))) {
            if (Debug.UNHANDLEDALL) { debug("Unhandled packet was "+event.dump()); }
            if (unhandled.contains(messageid)) { event.status(Event.STATUS.COMPLETE); return; }
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
                        String commandname=((CommandEvent)event).getName().toLowerCase();
                        if (Debug.TRACKCOMMANDS) { debug("Command "+commandname+" invoking delayed handler "+h.getClass().getSimpleName()); }
                        response=executeCommand(commandname,(CommandEvent)event,h);
                        if (response!=null && !response.equals("") && ((CommandEvent)event).respondTo()!=null) {
                            im(((CommandEvent)event).respondTo(),"> "+response);
                            ((CommandEvent)event).respondTo(response);
                        }
                        if (response!=null && !response.isEmpty()) { ((CommandEvent)event).response(response); }
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
    




    private String executeCommand(String name, CommandEvent event, Handler handler) {
        // find the target method
        String response;
        name=name.toLowerCase();
        try {
            Method method=getMethod(handler,name);
            List<Object> parameters=getParameters(method,event);
            response=call(handler,method,parameters);
        }
        catch (Exception t) {response="Command failed: "+t.toString(); warn("Command "+name+" failed",t); }
        return response;
    }

    private Method getMethod(Handler h, String fullname) {
        Method match=null;
        String[] nameparts=fullname.split("\\.");
        String name=nameparts[nameparts.length-1];
        for (Method m:h.getClass().getMethods()) {
            if (m.getName().toLowerCase().equals(name+"command") && m.getName().endsWith("Command")) {
                if (match!=null) { throw new IllegalArgumentException("Multiple coded commands called "+name+" in "+h.getClass().getName()); }
                match=m;
            }
        }
        if (match==null) { throw new IllegalArgumentException("No command called "+name+" exists in "+h.getClass().getName()); }
        if (match.getParameterCount()<1) { throw new IllegalArgumentException("Command must have at least one parameter, regional data, in command "+name+" class "+h.getClass().getName()); }
        if (match.getParameterTypes()[0]!=Regional.class) { throw new IllegalArgumentException("Command must take regional data as first parmeter, command is "+name+" in class "+h.getClass().getName()); }
        return match;
    }
    public String getHelp(String command) {
        command=command.toLowerCase();
        Handler handler=immediatehandlers.get("CMD/"+command).get(0);
        if (handler==null) { throw new IllegalArgumentException("Could not find command"); }
        Method m=getMethod(handler,command);
        String ret="\nCommand: "+command;
        if (m.getAnnotation(CmdHelp.class)!=null) { ret+="\n"+((CmdHelp)(m.getAnnotation(CmdHelp.class))).description(); }
        for (Parameter param:m.getParameters()) {
            if (!param.getType().equals(Regional.class)) {
                ret+="\n"+param.getName();
                if (param.getAnnotation(ParamHelp.class)!=null) {
                    ret+=" - "+param.getAnnotation(ParamHelp.class).description();
                }
            }
        }
        return ret;
    }

    private List<Object> getParameters(Method method, CommandEvent event) {
        List<Object> parameters=new ArrayList<>();
        parameters.add(event.region()); boolean firstparam=true;
        for (Parameter param:method.getParameters()) {
            //System.out.println(param.toString());
            if (firstparam) { firstparam=false; }
            else {
                String paramname=param.getName();
                if (event.parameters().containsKey(paramname)) { parameters.add(event.parameters().get(paramname)); }
                else { parameters.add(null); }
            }
        }
        return parameters;
    }

    private String call(Handler handler, Method method, List<Object> parameters) throws Exception {
        try {
            return (String) method.invoke(handler,parameters.toArray());
        } catch (IllegalAccessException ex) {
            throw new IllegalArgumentException("Command with incorrect access method - "+method.getName()+" in "+handler.getClass().getName());
        } catch (InvocationTargetException ex) {
            if (ex.getCause()==null) { throw new NullPointerException("Command InvocationTargetException with null initCause in "+method.getName()+" in "+handler.getClass().getName()); }
            if (ex.getCause() instanceof Exception) { throw ((Exception)(ex.getCause())); }
            throw ex;
        }
    }

    public Map<String,Map<String,String>> getCommands() {
        Map<String,Map<String,String>> ret=new HashMap<>();
        for (String name:delayedhandlers.keySet()) {
            if (name.startsWith("CMD/")) {
                name=name.substring(4);
                ret.put(name,null);
            }
        }
        return ret;
    }


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
        initialiseHandlers();
        performLogin(firstname,lastname,password,loginlocation);
        notifyHandlers();
        while (!quit) {
            Event event=null;
            synchronized(queue) {
                if (queue.isEmpty()) {
                    try { queue.wait(1000); }
                    catch (InterruptedException iex) {}
                }
                if (!queue.isEmpty()) { event=queue.remove(0); }
            }
            if (event!=null) {
                if (event instanceof Event) {
                    Event e=(Event)event;
                    try { distribute(e); }
                    catch (Exception ex) { error("Event distributor crashed, continuing, but that event is imcompletely handled"+e.getName(),ex); }
                } else { warn("Unknown class on event queue"+event.getClass().getName()); }
            }
            agentUpdate();

        }
        note("Bot exited: "+quitreason);
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
        synchronized(circuits) {
            Set<Circuit> ret=new HashSet<>();
            ret.addAll(circuits.values());
            return ret;
        }
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

    public void setFOV(float angle) throws IOException {
        AgentFOV fov=new AgentFOV();
        fov.bagentdata.vagentid=getUUID();
        fov.bagentdata.vcircuitcode=new U32(getCC());
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
