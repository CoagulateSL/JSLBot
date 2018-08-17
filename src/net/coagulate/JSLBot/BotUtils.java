package net.coagulate.JSLBot;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.coagulate.JSLBot.LLSD.LLSDArray;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

/** Some general purpose useful static functions.
 *
 * @author Iain Price
 */
public abstract class BotUtils {
    /** Turns a byte array into a hex string
     * @param in Byte array
     * @return Hex string representation.
     */
    public static String hex(byte[] in) {
        final StringBuilder builder = new StringBuilder();
        for(byte b : in) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }     
    
    /** Get the machine's mac address as a hex string, required by the SL login.
     * 
     * @return Mac address in hex form.
     * @throws UnknownHostException 
     * @throws SocketException 
     */
    public static String getMac() throws UnknownHostException, SocketException {
        // this used to be more intelligent but now we just iterate through cards and grab /a/ mac address.
        Enumeration<NetworkInterface> e=NetworkInterface.getNetworkInterfaces();
        byte[] mac=null;
        NetworkInterface stored=null;
        while (e.hasMoreElements() && mac==null) {
            NetworkInterface ni = e.nextElement();
            mac=ni.getHardwareAddress();
            if (mac!=null) { stored=ni; }
        }
        if (mac == null){
            if (stored==null) { throw new IllegalArgumentException("No network interfaces found"); }
            throw new IllegalArgumentException("No MAC on network interface found for "+stored.toString());
        }
        //System.out.println("Using mac "+hex(mac)+" from "+stored.toString());
        return hex(mac);
    }

    /** Hash a password using MD5 and prefix with $1$, used by SL login request.
     * If already prefixed with $!$, returned verbatim
     * @param password Password, cleartext or MD5 with $1$ prefix
     * @return MD5 hex hash of the password, with $1$ prefix, as used in SL login protocol
     * @throws NoSuchAlgorithmException MD5 is not supported (?)
     * @throws UnsupportedEncodingException UTF-8 is not supported
     */
    public static String md5hash(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if (password.startsWith("$1$")) {
            return password; // already hashed, or you have a really unfortunate choice of password :P
        } 
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] digest = md5.digest(password.getBytes("UTF-8"));
        return "$1$" + hex(digest);
    }
 
    /** Create and execute the XMLRPC logon command.
     * 
     * @param bot The owning bot making the connection
     * @param firstname Login first name
     * @param lastname Login last name (maybe 'resident')
     * @param password Password, in clear text or MD5 hex string preceeded by $!$
     * @param location Login location
     * @return Map of KV pairs from login server
     * @throws MalformedURLException
     * @throws UnknownHostException
     * @throws SocketException
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws XmlRpcException 
     */
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

    /** A list of caps we request from a sim.
     * 
     * @return 
     */
    public static LLSDArray getCAPSArray() {
        //Mostly here because its a giant horrible block of text
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
        req.add("GroupMemberData");
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
    
    /** ZeroEncode the input byte array.
     * Add acks /after/ zerocoding, they are not included.
     * See the SL protocol documentation.
     * @param input raw byte array
     * @return ZeroCoded byte array
     */
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
        }
        byte[] outputbytes = new byte[output.size()];
        int offset=0;
        // and put it back into the byte array :P
        for (Byte b:output) {
            outputbytes[offset]=b; offset++;
        }    
        return outputbytes;
    }

    /** Read a zero byte terminated string from a byte buffer.
     * 
     * @param buffer
     * @return 
     */
    public static String readZeroTerminatedString(ByteBuffer buffer) {
        List<Byte> bytes=new ArrayList<>();
        byte b=-1;
        while (b!=0) {
            b=buffer.get();
            if (b>0) { bytes.add(b); }
        }
        Byte[] bytesarray=bytes.toArray(new Byte[0]);
        byte[] ba=new byte[bytesarray.length]; 
        for (int i=0;i<bytesarray.length;i++) { ba[i]=bytesarray[i]; }
        return new String(ba);
    }
}
