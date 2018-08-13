package net.coagulate.JSLBot;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import net.coagulate.JSLBot.LLSD.Atomic;
import net.coagulate.JSLBot.LLSD.LLSD;
import net.coagulate.JSLBot.LLSD.LLSDArray;
import net.coagulate.JSLBot.LLSD.LLSDMap;
import net.coagulate.JSLBot.LLSD.LLSDString;
import net.coagulate.JSLBot.LLSD.LLSDUUID;
import net.coagulate.JSLBot.Packets.Types.LLUUID;

/**
 *
 * @author Iain Price
 */
public class CAPS {
    private String caps;
    private LLSDMap capabilities;
    private String displaynames;
    private Circuit circuit;
    private EventQueue eq=null; EventQueue eventqueue() { return eq; }
    public JSLBot bot() { return circuit().bot(); }
    
    /** Create and interrogate CAPS.
     * Launch EventQueueGet if applicable
     * @param circuit Owning circuit
     * @param capsseed CAPS url
     * @throws IOException 
     */
    public CAPS(Circuit circuit,String capsseed) throws IOException {
        this.caps=capsseed;
        this.circuit=circuit;
        LLSDArray req=BotUtils.getCAPSArray();
        LLSD getcaps=new LLSD(req);
        
        capabilities=invokeXML(caps,getcaps);

        if (capabilities.containsKey("GetDisplayNames")) { displaynames=((LLSDString)(capabilities.get("GetDisplayNames"))).toString(); }
        if (capabilities.containsKey("EventQueueGet")) {
            eq=new EventQueue(this,((LLSDString)capabilities.get("EventQueueGet")).toString(),circuit.handle());
            eq.setDaemon(true);
            eq.start();
            info("CAPS seed interrogated successfully; EventQueueGet driver launched");
        } else {
            error("CAPS seed interrogated successfully; There was NO EVENTQUEUEGET CAPABILITY!!! Without this we are unable to successfully change region circuits - we are bound to the present sim.  This is neither normal or expected behaviour.");
            throw new IOException("Second Life CAPS enquiry failed to produce an EventQueueGet capability; this is treated as fatal due to the funcionality loss this will cause.");
        }
    }
    
    /** Run a CAPS getDisplayNames event for a given UUID.
     * Does /not/ use the cache, internal use only!
     * @param agentid
     * @throws MalformedURLException
     * @throws IOException 
     */
    void getNames(LLUUID agentid) throws MalformedURLException, IOException {
        LLSDMap map = invokeCAPS("GetDisplayNames","/?ids="+agentid.toUUIDString(),null);
        LLSDArray agents=(LLSDArray) map.get("agents");
        for (Object agento:agents) {
            LLSDMap agent=(LLSDMap) agento;
            //System.out.println(agent.toXML());
            LLSDUUID describedagent=(LLSDUUID) agent.get("id");
            LLSDString displayname=(LLSDString) agent.get("display_name");
            LLSDString firstname=(LLSDString) agent.get("legacy_first_name");
            LLSDString lastname=(LLSDString) agent.get("legacy_last_name");
            LLSDString username=(LLSDString) agent.get("username");
            Global.displayName(describedagent.toLLUUID(), displayname.toString());
            Global.firstName(describedagent.toLLUUID(), firstname.toString());
            Global.lastName(describedagent.toLLUUID(), lastname.toString());
            Global.userName(describedagent.toLLUUID(), username.toString());
        }
    }

    /** Call a specific cap, with a suffix and an optional document */
    public LLSDMap invokeCAPS(String capname,String appendtocap,LLSD content) throws IOException
    {
        Atomic rawcap = capabilities.get(capname);
        if (rawcap==null) {
            //for (String cap:capabilities.keys()) { System.out.println("KNOWN CAP: "+cap); }
            throw new IOException("Unknown CAPS "+capname);
        }
        String cap=rawcap.toString();
        return invokeXML(cap+appendtocap,content);
    }
    /** Call a URL for an XML exchange
     * 
     * @param url Target URL
     * @param content LLSD document to POST, or null if GET mode only
     * @return LLSDMap response
     * @throws MalformedURLException
     * @throws IOException 
     */
    public LLSDMap invokeXML(String url,LLSD content) throws MalformedURLException, IOException {
        if (url==null || url.isEmpty()) { throw new IllegalArgumentException("Null or empty URL passed."); }
        HttpURLConnection connection=(HttpURLConnection) new URL(url).openConnection();
        byte[] postdata=new byte[0];
        if (content==null) {
            connection.setRequestMethod("GET");
            connection.setDoOutput(false);
        } else {
            postdata=(content.toXML()).getBytes(StandardCharsets.UTF_8);;
            connection.setRequestProperty("Content-Length",Integer.toString(postdata.length));
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
        }
        connection.setRequestProperty("Content-Type","application/llsd+xml");
        connection.setRequestProperty("charset","utf-8");
        connection.setUseCaches(false);
        if (content!=null) {
            try( DataOutputStream wr = new DataOutputStream( connection.getOutputStream())) {
              wr.write( postdata );
            }
        }
        Reader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        String read="";
        for (int c; (c = in.read()) >= 0;)
            read=read+((char)c);
        //System.out.println(read);
        return (LLSDMap) new LLSD(read).getFirst();
        
    }    
    
    Circuit circuit() {
        return circuit;
    }
    public String regionName() { return circuit.getRegionName(); }
    void debug(String message) { debug(message,null); }
    void debug(String message, Throwable t) { Log.log(bot(),Log.DEBUG,"("+regionName()+") "+message,t); }
    void info(String message) { info(message,null); }
    void info(String message, Throwable t) { Log.log(bot(),Log.INFO,"("+regionName()+") "+message,t); }
    void note(String message) { note(message,null); }
    void note(String message, Throwable t) { Log.log(bot(),Log.NOTE,"("+regionName()+") "+message,t); }
    void warn(String message) { warn(message,null); }
    void warn(String message, Throwable t) { Log.log(bot(),Log.WARNING,"("+regionName()+") "+message,t); }
    void error(String message) { error(message,null); }
    void error(String message, Throwable t) { Log.log(bot(),Log.ERROR,"("+regionName()+") "+message,t); }
    void crit(String message) { crit(message,null); }
    void crit(String message, Throwable t) { Log.log(bot(),Log.CRITICAL,"("+regionName()+") "+message,t); }    





}
