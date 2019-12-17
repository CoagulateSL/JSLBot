package net.coagulate.JSLBot.LLSD;

import org.w3c.dom.Node;

import javax.annotation.Nonnull;
import java.nio.ByteBuffer;
import java.util.Base64;

/** Represent a binary object in LLSD format.
 * Uses BASE64 encoding.
 * @author Iain Price
 */
public class LLSDBinary extends Atomic {

    final String value;
    public LLSDBinary(final String s) {
        value=s;
    }

    LLSDBinary(@Nonnull final Node item) {
        value=item.getTextContent();
    }
    public LLSDBinary(final int a) {
        value=Base64.getEncoder().encodeToString(ByteBuffer.allocate(4).putInt(a).array());
    }

    @Nonnull
    @Override
    public String toXML(final String lineprefix) {
        return lineprefix+"<binary encoding=\"base64\">"+value+"</binary>\n";
    }
    @Override
    public String toString() { return value; }
    public byte[] toByte() { return Base64.getDecoder().decode(value); }

    @Nonnull
    public String toIP() {
        final byte[] ipbyte=toByte();
        return (ipbyte[0]&0xff) +"."+ (ipbyte[1]&0xff) +"."+ (ipbyte[2]&0xff) +"."+ (ipbyte[3]&0xff);
    }

    public long toLong() {
        final byte[] by=toByte();
        long longvalue = 0;
        for (int i = 0; i < by.length; i++)
        {
           longvalue += ((long) by[by.length-1-i] & 0xffL) << (8 * i);
        }
        return longvalue;
    }
}

