/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.coagulate.JSLBot;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import javax.xml.parsers.ParserConfigurationException;
import net.coagulate.JSLBot.LLSD.Atomic;
import net.coagulate.JSLBot.LLSD.LLSDArray;
import net.coagulate.JSLBot.LLSD.LLSD;
import net.coagulate.JSLBot.LLSD.LLSDMap;
import net.coagulate.JSLBot.LLSD.LLSDString;
import net.coagulate.JSLBot.LLSD.LLSDUUID;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import org.xml.sax.SAXException;

/**
 *
 * @author Iain
 */
public class CAPS extends Thread {
    String caps;
    LLSDMap capabilities;
    private String displaynames;
    Circuit circuit;
    public void run() {
        try {
            //runMain();
        }
        catch (Exception e)
        {
            error("CAPS thread crashed: "+e,e);
        }
    }
    public JSLBot bot() { return getCircuit().bot(); }
    public CAPS(Circuit circuit,String capsseed,long regionhandle) throws IOException {
        this.caps=capsseed;
        this.circuit=circuit;
        URL url=new URL(caps);
        LLSDArray req=new LLSDArray();
        req.add("AttachmentResources");
        req.add("AvatarPickerSearch");
        req.add("ChatSessionRequest");
        req.add("CopyInventoryFromNotecard");
        req.add("DispatchRegionInfo");
        req.add("EstateChangeInfo");
        req.add("EventQueueGet");
        req.add("ObjectMedia");
        req.add("ObjectMediaNavigate");
        req.add("FetchLib2");
        req.add("FetchLibDescendents2");
        req.add("FetchInventory2");
        req.add("FetchInventoryDescendents2");
        req.add("GetDisplayNames");
        req.add("GetTexture");
        req.add("GetMesh");
        req.add("GetObjectCost");
        req.add("GetObjectPhysicsData");
        req.add("GroupProposalBallot");
        req.add("HomeLocation");
        req.add("LandResources");
        req.add("MapLayer");
        req.add("MapLayerGod");
        req.add("NewFileAgentInventory");
        req.add("NewFileAgentInventoryVariablePrice");
        req.add("Objectadd");
        req.add("ParcelPropertiesUpdate");
        req.add("ParcelMediaURLFilterList");
        req.add("ParcelNavigateMedia");
        req.add("ParcelVoiceInfoRequest");
        req.add("ProductInfoRequest");
        req.add("ProvisionVoiceAccountRequest");
        req.add("RemoteParcelRequest");
        req.add("RequestTextureDownload");
        req.add("SearchStatRequest");
        req.add("SearchStatTracking");
        req.add("SendPostcard");
        req.add("SendUserReport");
        req.add("SendUserReportWithScreenshot");
        req.add("ServerReleaseNotes");
        req.add("SimConsole");
        req.add("SimulatorFeatures");
        req.add("SetDisplayName");
        req.add("SimConsoleAsync");
        req.add("StartGroupProposal");
        req.add("TextureStats");
        req.add("UntrustedSimulatorMessage");
        req.add("UpdateAgentInformation");
        req.add("UpdateAgentLanguage");
        req.add("UpdateGestureAgentInventory");
        req.add("UpdateNotecardAgentInventory");
        req.add("UpdateScriptAgent");
        req.add("UpdateGestureTaskInventory");
        req.add("UpdateNotecardTaskInventory");
        req.add("UpdateScriptTask");
        req.add("UploadBakedTexture");
        req.add("UploadObjectAsset");
        req.add("ViewerMetrics");
        req.add("ViewerStartAuction");
        req.add("ViewerStats");        

        LLSD getcaps=new LLSD(req);
        
        //debug("CAPS POST:"+req.toXML());
        byte[] postdata=(getcaps.toXML()).getBytes(StandardCharsets.UTF_8);
        
        HttpURLConnection connection=(HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type","application/llsd+xml");
        connection.setRequestProperty("charset","utf-8");
        connection.setRequestProperty("Content-Length",Integer.toString(postdata.length));
        connection.setUseCaches(false);
        
        try( DataOutputStream wr = new DataOutputStream( connection.getOutputStream())) {
              wr.write( postdata );
        }
        Reader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        String read="";
        for (int c; (c = in.read()) >= 0;)
            read=read+((char)c);
        capabilities=(LLSDMap) new LLSD(read).getFirst();
        if (capabilities.containsKey("GetDisplayNames")) { displaynames=((LLSDString)(capabilities.get("GetDisplayNames"))).toString(); }
        if (capabilities.containsKey("EventQueueGet")) {
            EventQueue eq=new EventQueue(this,((LLSDString)capabilities.get("EventQueueGet")).toString(),regionhandle);
            eq.start();
            info("CAPS seed interrogated successfully; EventQueueGet driver launched");
        } else {
            error("CAPS seed interrogated successfully; There was NO EVENTQUEUEGET CAPABILITY!!! Without this we are unable to successfully change region circuits - we are bound to the present sim.  This is neither normal or expected behaviour.");
            throw new IOException("Second Life CAPS enquiry failed to produce an EventQueueGet capability; this is treated as fatal due to the funcionality loss this will cause.");
        }
    }
    
    
    
    void getNames(LLUUID agentid) throws MalformedURLException, IOException {
        HttpURLConnection connection=(HttpURLConnection) new URL(displaynames+"/?ids="+agentid.toUUIDString()).openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(false);
        connection.setRequestProperty("Content-Type","application/llsd+xml");
        connection.setRequestProperty("charset","utf-8");
        connection.setUseCaches(false);
        Reader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        String read="";
        for (int c; (c = in.read()) >= 0;)
            read=read+((char)c);
        LLSDMap map = (LLSDMap) new LLSD(read).getFirst();               
        LLSDArray agents=(LLSDArray) map.get("agents");
        for (Object agento:agents) {
            LLSDMap agent=(LLSDMap) agento;
            //System.out.println(agent.toXML());
            LLSDUUID describedagent=(LLSDUUID) agent.get("id");
            LLSDString displayname=(LLSDString) agent.get("display_name");
            LLSDString firstname=(LLSDString) agent.get("legacy_first_name");
            LLSDString lastname=(LLSDString) agent.get("legacy_last_name");
            LLSDString username=(LLSDString) agent.get("username");
            Global.setDisplayName(describedagent.toLLUUID(), displayname.toString());
            Global.setFirstName(describedagent.toLLUUID(), firstname.toString());
            Global.setLastName(describedagent.toLLUUID(), lastname.toString());
            Global.setUserName(describedagent.toLLUUID(), username.toString());
        }
    }

    Circuit getCircuit() {
        return circuit;
    }
    String getRegionName() { return circuit.getRegionName(); }
    void debug(String message) { debug(message,null); }
    void debug(String message, Throwable t) { Log.log(bot(),Log.DEBUG,"("+getRegionName()+") "+message,t); }
    void info(String message) { info(message,null); }
    void info(String message, Throwable t) { Log.log(bot(),Log.INFO,"("+getRegionName()+") "+message,t); }
    void note(String message) { note(message,null); }
    void note(String message, Throwable t) { Log.log(bot(),Log.NOTE,"("+getRegionName()+") "+message,t); }
    void warn(String message) { warn(message,null); }
    void warn(String message, Throwable t) { Log.log(bot(),Log.WARNING,"("+getRegionName()+") "+message,t); }
    void error(String message) { error(message,null); }
    void error(String message, Throwable t) { Log.log(bot(),Log.ERROR,"("+getRegionName()+") "+message,t); }
    void crit(String message) { crit(message,null); }
    void crit(String message, Throwable t) { Log.log(bot(),Log.CRITICAL,"("+getRegionName()+") "+message,t); }    




}
