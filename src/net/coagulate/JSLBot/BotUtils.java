/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.coagulate.JSLBot;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
    
}
