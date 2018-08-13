package net.coagulate.JSLBot.LLSD;

import java.nio.ByteBuffer;
import java.util.Base64;
import org.w3c.dom.Node;

/**
 *
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
    public String toString() { return value; }
    public byte[] toByte() { return Base64.getDecoder().decode(value); }

    public String toIP() {
        byte[] ipbyte=toByte();
        String numericip=(int)(ipbyte[0]&0xff)+"."+(int)(ipbyte[1]&0xff)+"."+(int)(ipbyte[2]&0xff)+"."+(int)(ipbyte[3]&0xff);
        return numericip;
    }

    public long toLong() {
        byte[] by=toByte();
        long value = 0;
        for (int i = 0; i < by.length; i++)
        {
           value += ((long) by[by.length-1-i] & 0xffL) << (8 * i);
        }
        return value;
    }
}

