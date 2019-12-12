package net.coagulate.JSLBot.LLSD;

import org.w3c.dom.Node;

import java.nio.ByteBuffer;
import java.util.Base64;

/** Represent a binary object in LLSD format.
 * Uses BASE64 encoding.
 * @author Iain Price
 */
public class LLSDBinary extends Atomic {

    String value="";
    public LLSDBinary(String s) {
        value=s;
    }

    LLSDBinary(Node item) {
        value=item.getTextContent();
    }
    public LLSDBinary(int a) {
        value=Base64.getEncoder().encodeToString(ByteBuffer.allocate(4).putInt(a).array());
    }

    @Override
    public String toXML(String lineprefix) {
        return lineprefix+"<binary encoding=\"base64\">"+value+"</binary>\n";
    }
    @Override
    public String toString() { return value; }
    public byte[] toByte() { return Base64.getDecoder().decode(value); }

    public String toIP() {
        byte[] ipbyte=toByte();
        return (ipbyte[0]&0xff) +"."+ (ipbyte[1]&0xff) +"."+ (ipbyte[2]&0xff) +"."+ (ipbyte[3]&0xff);
    }

    public long toLong() {
        byte[] by=toByte();
        long longvalue = 0;
        for (int i = 0; i < by.length; i++)
        {
           longvalue += ((long) by[by.length-1-i] & 0xffL) << (8 * i);
        }
        return longvalue;
    }
}

