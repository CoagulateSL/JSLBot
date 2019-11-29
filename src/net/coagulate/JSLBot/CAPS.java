package net.coagulate.JSLBot;

import net.coagulate.JSLBot.LLSD.*;
import net.coagulate.JSLBot.Packets.Types.LLUUID;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;

/** Handles a region's CAPabilitieS, a map of URLs and named endpoints.
 *
 * @author Iain Price
 */
public final class CAPS extends Thread {
    private final Logger log;
    // caps URL
    private final String caps;
    // retrieved capabilities
    private LLSDMap capabilities;
    private final Circuit circuit;
    private EventQueue eq=null; EventQueue eventqueue() { return eq; }
    
    /** Create and interrogate CAPS.
     * Note: This just prepares the CAPS, you must complete the initialistion with either:
     * You can call 'initialise()' and then 'launchEventQueue()' to connect in THIS thread.
     * *OR* you can call start() which will perform both these operations in a background thread.
     * @param circuit Owning circuit
     * @param capsseed CAPS url
     */
    public CAPS(Circuit circuit,String capsseed) {
        log=circuit.getLogger("CAPS");
        this.caps=capsseed;
        this.circuit=circuit;
    }
    
    /** Initialises and launches the event queue, in a background thread */
    @Override
    public void run() {
        try {
            initialise();
            launchEventQueue();
        } catch (IOException e) {
            log.log(SEVERE,"CAPS setup failed: "+e.toString(),e);
        }
    }
    /** Initialise the CAPS - download the CAPS list from the server */
    private void initialise() throws IOException {
        LLSDArray req=BotUtils.getCAPSArray();
        LLSD getcaps=new LLSD(req);
        capabilities=invokeXML(caps,getcaps);
    }
    private boolean launched=false;
    /** Launch the event queue driver, if the CAPS exists, which it should */
    private synchronized void launchEventQueue() {
        if (launched){return; }
        launched=true;
        if (capabilities.containsKey("EventQueueGet")) {
            eq=new EventQueue(this, capabilities.get("EventQueueGet").toString());
            eq.setDaemon(true);
            eq.start();
            log.info("CAPS seed interrogated successfully; EventQueueGet driver launched");
        } else {
            log.severe("CAPS seed interrogated successfully; There was NO EVENTQUEUEGET CAPABILITY!!! Without this we are unable to successfully change region circuits - we are bound to the present sim.  This is neither normal or expected behaviour.");
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

    /** Call a specific cap, with a suffix and an optional document.
     * The list of CAPS intially requested is configured in BotUtils.
     * @param capname Name of the CAP, as requested
     * @param appendtocap URL suffix for the CAP
     * @param content LLSD Document to supply to the CAP
     * @return The LLSDMap response
     * @throws IOException If the CAP fails, or the CAP requested is not known to us
     */
    public LLSDMap invokeCAPS(String capname,String appendtocap,LLSD content) throws IOException
    {
        if (capabilities==null) { throw new NullPointerException("CAPS not yet established"); }
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
     * @throws IOException If there is a failure with the CAPS
     */
    public LLSDMap invokeXML(String url,LLSD content) throws IOException {
        if (url==null || url.isEmpty()) { throw new IllegalArgumentException("Null or empty URL passed."); }
        HttpURLConnection connection=(HttpURLConnection) new URL(url).openConnection();
        byte[] postdata=new byte[0];
        if (content==null) {
            connection.setRequestMethod("GET");
            connection.setDoOutput(false);
        } else {
            postdata=(content.toXML()).getBytes(StandardCharsets.UTF_8);
            connection.setRequestProperty("Content-Length",Integer.toString(postdata.length));
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
        }
        connection.setReadTimeout(15000);
        connection.setRequestProperty("Content-Type","application/llsd+xml");
        connection.setRequestProperty("charset","utf-8");
        connection.setUseCaches(false);
        if (content!=null) {
            try( DataOutputStream wr = new DataOutputStream( connection.getOutputStream())) {
                wr.write( postdata );
            }
        }
        Scanner s=new Scanner(connection.getInputStream()).useDelimiter("\\A");
        String read=s.next();
        return (LLSDMap) new LLSD(read).getFirst();
    }    
    
    Circuit circuit() {
        return circuit;
    }
    public String regionName() { return circuit.getRegionName(); }
    @Override
    public String toString() { return circuit.toString()+" / CAPS"; }

    public Logger getLogger(String subspace) {
        return Logger.getLogger(log.getName()+"."+subspace);
    }
}
