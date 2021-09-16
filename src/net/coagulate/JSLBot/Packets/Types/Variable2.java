package net.coagulate.JSLBot.Packets.Types;

import javax.annotation.Nonnull;
import java.nio.ByteBuffer;

/**
 *
 * @author Iain Price
 */
public class Variable2 extends Type {

    @Nonnull
    public  byte[] value=new byte[0];

    public Variable2(){}
    public Variable2(@Nonnull String string) {
        @Nonnull char[] array=string.toCharArray();
        value=new byte[array.length+1];
        for (int i=0;i<array.length;i++) { value[i]=(byte) array[i]; }
        value[value.length-1]=0;
    }
    @Override
    public int size() {
        return value.length+(new U16().size());
    }

    @Override
    public void read(@Nonnull ByteBuffer in) {
        @Nonnull U16 length=new U16();
        length.read(in);
        int len=((int)(length.value)) & 0xffff;
        value=new byte[len];
        for (int i=0;i<len;i++) {
            value[i]=in.get();
        }
    }

    @Override
    public void write(@Nonnull ByteBuffer out) {
        @Nonnull U16 length=new U16();
        length.value=(short) value.length;
        length.write(out);
        out.put(value);
    }

    @Nonnull
    @Override
    public String dump() {
        return "'"+toString()+"'";
    }
    @Nonnull
    public String toString() {
        @Nonnull String str="";
        for (byte b:value) {
            if (b>0) { str=str+(char)b; }
        }
        return str;
    }
        
}
