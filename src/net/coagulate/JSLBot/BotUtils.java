package net.coagulate.JSLBot;

import net.coagulate.JSLBot.LLSD.LLSDArray;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

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
     */
    public static String getMac() {
        try {
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
                throw new IllegalArgumentException("No network interfaces found");
            }
            //System.out.println("Using mac "+hex(mac)+" from "+stored.toString());
            return hex(mac);
        } catch (SocketException ex) {
            throw new AssertionError("Unable to retrieve any network interfaces MAC addres; unsupported platform or no networking present???",ex);
        }
    }

    /** Hash a password using MD5 and prefix with $1$, used by SL login request.
     * If already prefixed with $!$, returned verbatim
     * @param password Password, cleartext or MD5 with $1$ prefix
     * @return MD5 hex hash of the password, with $1$ prefix, as used in SL login protocol
     */
    public static String md5hash(String password) {
        if (password.startsWith("$1$")) {
            return password; // already hashed, or you have a really unfortunate choice of password :P
        } 
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            throw new AssertionError("MD5 hashing is not supported on this platform?");
        }
        byte[] digest;
        digest = md5.digest(password.getBytes(StandardCharsets.UTF_8));
        return "$1$" + hex(digest);
        //return hex(digest);
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
     * @throws XmlRpcException
     */
    static Map<Object,Object> loginXMLRPC(JSLBot bot,String firstname,String lastname,String password,String location) throws MalformedURLException, XmlRpcException {
        XmlRpcClientConfigImpl config=new XmlRpcClientConfigImpl();
        config.setServerURL(new URL("https://login.agni.lindenlab.com/cgi-bin/login.cgi"));
        XmlRpcClient client=new XmlRpcClient();
        client.setConfig(config);
        HashMap<String,Object> params=new HashMap<>();
        params.put("first",firstname);
        params.put("last",lastname);
        params.put("extended_errors",1);
        params.put("start",location);
        params.put("channel","JSLBot <Iain Maltz@Second Life>");
        params.put("platform","Lin");
        List<String> options=new ArrayList<>();
        options.add("inventory-root");
        options.add("adult-compliant");
        options.add("buddy-list");
        options.add("login-flags");
        params.put("options",options);
        String mac=BotUtils.getMac();
        params.put("mac",mac);
        // MD-5 =)
        // TURNS OUT SECOND LIFE ONLY USES THE FIRST 16 CHARS
        // but silently discards the rest in the user interface, so you can have >16 chars, but the rest dont do anything.
        // However, if you MD5sum more than 16 chars you break the world.
        if (password.length()>16 && (!password.startsWith("$1$"))) { password=password.substring(0,16); }
        params.put("passwd",BotUtils.md5hash(password));
        if (Debug.AUTH) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                System.out.println(entry.getKey() +"="+ entry.getValue().toString());
            }
        }
        Object resultobject=(client.execute("login_to_simulator",new Object[]{params}));
        @SuppressWarnings("unchecked") HashMap<Object,Object> result=(HashMap<Object,Object>)resultobject;
        if (Debug.AUTH) {
            // dump the result
            for(Map.Entry<Object, Object> entry : result.entrySet()) {
                String printline=(entry.getKey() +" -> ");
                Object output= entry.getValue();
                if (output instanceof String) { printline+=("[String] "+ output); }
                else {
                    if (output instanceof Integer) { printline+=("[Integer] "+ output); }
                    else {
                        String clas= entry.getValue().getClass().getTypeName();
                        printline+="["+clas+"] "+(output);
                    }
                }
                bot.getLogger("Login").finer(printline);
            }
        }
        return result;
    }

    /** A list of caps we request from a sim.
     * 
     * @return Array of CAPS
     */
    static LLSDArray getCAPSArray() {
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
     * @param buffer Byte buffer containing a zero terminated string.
     * @return String read up to the zero byte.
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

    public static String unravel(Throwable t) {
        StringBuilder response= new StringBuilder();
        while (t!=null) {
            response.append("\n[").append(t.getLocalizedMessage()).append("]");
            t=t.getCause();
        }
        return response.toString();
    }
}
