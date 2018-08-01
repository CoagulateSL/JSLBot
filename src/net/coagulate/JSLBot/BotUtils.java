/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.coagulate.JSLBot;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.coagulate.JSLBot.LLSD.LLSDArray;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

/** Some general purpose useful stuff
 *
 * @author Iain Price <git@predestined.net>
 */
public abstract class BotUtils {
    public static String hex(byte[] in) {
        final StringBuilder builder = new StringBuilder();
        for(byte b : in) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }     
    
    // used by the SL login protocol
    public static String getMac() throws UnknownHostException, SocketException {
        
        InetAddress addr = InetAddress.getLocalHost();
        NetworkInterface ni = NetworkInterface.getByInetAddress(addr);
        if (ni == null)
            return null;

        byte[] mac = ni.getHardwareAddress();
        if (mac == null)
            return null;

        StringBuilder sb = new StringBuilder(18);
        for (byte b : mac) {
            if (sb.length() > 0)
                sb.append(':');
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    /*// stuff you probably want to reimplement by extending this class
    public void alert(String alert) {}
    public void chat(String from,LLUUID source,LLUUID owner,LLVector3 position,String message) {}
    public void groupMessage(String from,LLUUID source,String message) {}
    public void instantMessage(String from,LLUUID source,String message) throws IOException {}
    public void healthChange() {}
    public void positionUpdate() {}
    public void sendInstantMessage(LLUUID target,String message) throws IOException {
    ImprovedInstantMessage i=new ImprovedInstantMessage();
    i.setReliable(true);
    i.bagentdata.vagentid=new LLUUID(uuid);
    i.bagentdata.vsessionid=sessionid;
    i.bmessageblock.vmessage=new Variable2(message);
    i.bmessageblock.vfromagentname=new Variable1(getFullName());
    i.bmessageblock.vtoagentid=target;
    primary.send(i);
    }
     */
    public static String md5hash(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if (password.startsWith("$1$")) {
            return password;
        } // already hashed, or you have a really unfortunate choice of password :P
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] digest = md5.digest(password.getBytes("UTF-8"));
        return "$1$" + hex(digest);
    }
 
    
    public static Map loginXMLRPC(JSLBot bot,String firstname,String lastname,String password,String location) throws MalformedURLException, UnknownHostException, SocketException, NoSuchAlgorithmException, UnsupportedEncodingException, XmlRpcException {
        XmlRpcClientConfigImpl config=new XmlRpcClientConfigImpl();
        config.setServerURL(new URL("https://login.agni.lindenlab.com/cgi-bin/login.cgi"));
        XmlRpcClient client=new XmlRpcClient();
        client.setConfig(config);
        HashMap params=new HashMap();
        params.put("first",firstname);
        params.put("last",lastname);
        params.put("start",location);
        params.put("channel","JSLBot <Iain Maltz@Second Life>");
        params.put("platform","Lin");
        String mac=BotUtils.getMac();
        if (mac==null) { throw new IllegalArgumentException("Failed to get MAC address"); } else { params.put("mac",mac); }
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
                Log.debug(bot,printline);
            }
        }
        return result;
    }

    public static LLSDArray getCAPSArray() {
        LLSDArray req = new LLSDArray();
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
        return req;
    }
    
    
    public static byte[] zeroEncode(byte[] input) {
        List<Byte> output=new ArrayList<>();
        // first 5 bytes (header) are not encoded
        for (int i=0;i<6;i++) { output.add(input[i]); }
        int zerocount=0;
        // rest is
        for (int i=6;i<input.length;i++) {
            if (input[i]==0) {
                zerocount++;
            } else {
                if (zerocount>0) {
                    output.add((byte)0);
                    output.add((byte)zerocount);
                    zerocount=0;
                }
                output.add(input[i]);
            }
        }
        if (zerocount>0) {
            output.add((byte)0);
            output.add((byte)zerocount);
            zerocount=0;
        }
        byte[] outputbytes = new byte[output.size()];
        int offset=0;
        // and put it back into the byte array :P
        for (Byte b:output) {
            outputbytes[offset]=b; offset++;
        }    
        return outputbytes;
    }
}
