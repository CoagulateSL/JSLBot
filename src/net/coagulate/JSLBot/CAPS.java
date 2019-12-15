package net.coagulate.JSLBot;

import net.coagulate.JSLBot.LLSD.*;
import net.coagulate.JSLBot.Packets.Types.LLUUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
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
    @Nullable
    private LLSDMap capabilities;
    @Nonnull
    private LLSDMap capabilities() {
        if (capabilities==null) { throw new IllegalStateException("Accessing capabilities before it is ready"); }
        return capabilities;
    }

    @Nonnull
    private final Circuit circuit;
    @Nullable
    private EventQueue eq; @Nullable
    EventQueue eventqueue() { return eq; }
    
    /** Create and interrogate CAPS.
     * Note: This just prepares the CAPS, you must complete the initialistion with either:
     * You can call 'initialise()' and then 'launchEventQueue()' to connect in THIS thread.
     * *OR* you can call start() which will perform both these operations in a background thread.
     * @param circuit Owning circuit
     * @param capsseed CAPS url
     */
    public CAPS(@Nonnull final Circuit circuit, final String capsseed) {
        log=circuit.getLogger("CAPS");
        caps =capsseed;
        this.circuit=circuit;
    }
    
    /** Initialises and launches the event queue, in a background thread */
    @Override
    public void run() {
        try {
            initialise();
            launchEventQueue();
        } catch (final IOException e) {
            log.log(SEVERE,"CAPS setup failed: "+ e,e);
        }
    }
    /** Initialise the CAPS - download the CAPS list from the server */
    private void initialise() throws IOException {
        final LLSDArray req=BotUtils.getCAPSArray();
        final LLSD getcaps=new LLSD(req);
        capabilities=invokeXML(caps,getcaps);
    }
    private boolean launched;
    /** Launch the event queue driver, if the CAPS exists, which it should */
    private synchronized void launchEventQueue() {
        if (launched){return; }
        launched=true;
        if (capabilities().containsKey("EventQueueGet")) {
            eq=new EventQueue(this, capabilities().get("EventQueueGet").toString());
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
    void getNames(@Nonnull final LLUUID agentid) throws MalformedURLException, IOException {
        final LLSDMap map = invokeCAPS("GetDisplayNames","/?ids="+agentid.toUUIDString(),null);
        final LLSDArray agents=(LLSDArray) map.get("agents");
        for (final Object agento:agents) {
            final LLSDMap agent=(LLSDMap) agento;
            //System.out.println(agent.toXML());
            final LLSDUUID describedagent=(LLSDUUID) agent.get("id");
            final LLSDString displayname=(LLSDString) agent.get("display_name");
            final LLSDString firstname=(LLSDString) agent.get("legacy_first_name");
            final LLSDString lastname=(LLSDString) agent.get("legacy_last_name");
            final LLSDString username=(LLSDString) agent.get("username");
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
    @Nonnull
    public LLSDMap invokeCAPS(final String capname, final String appendtocap, final LLSD content) throws IOException
    {
        final Atomic rawcap = capabilities().get(capname);
        if (rawcap==null) {
            //for (String cap:capabilities.keys()) { System.out.println("KNOWN CAP: "+cap); }
            throw new IOException("Unknown CAPS "+capname);
        }
        final String cap=rawcap.toString();
        return invokeXML(cap+appendtocap,content);
    }
    /** Call a URL for an XML exchange
     * 
     * @param url Target URL
     * @param content LLSD document to POST, or null if GET mode only
     * @return LLSDMap response
     * @throws IOException If there is a failure with the CAPS
     */
    @Nonnull
    public LLSDMap invokeXML(@Nullable final String url, @Nullable final LLSD content) throws IOException {
        if (url==null || url.isEmpty()) { throw new IllegalArgumentException("Null or empty URL passed."); }
        final HttpURLConnection connection=(HttpURLConnection) new URL(url).openConnection();
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
            try(final DataOutputStream wr = new DataOutputStream( connection.getOutputStream())) {
                wr.write( postdata );
            }
        }
        final Scanner s=new Scanner(connection.getInputStream()).useDelimiter("\\A");
        final String read=s.next();
        final LLSDMap ret= (LLSDMap) new LLSD(read).getFirst();
        if (ret==null) { throw new IOException("Map read was null"); }
        return ret;
    }    
    
    @Nonnull
    Circuit circuit() {
        return circuit;
    }
    public String regionName() { return circuit.getRegionName(); }
    @Nonnull
    @Override
    public String toString() { return circuit +" / CAPS"; }

    public Logger getLogger(final String subspace) {
        return Logger.getLogger(log.getName()+"."+subspace);
    }

}
