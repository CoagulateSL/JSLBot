package net.coagulate.JSLBot.Packets.Types;

import java.nio.ByteBuffer;

/**
 *
 * @author Iain Price
 */
public class Variable2 extends Type {

    public  byte[] value=new byte[0];

    public Variable2(){}
    public Variable2(String string) {
        char[] array=string.toCharArray();
        value=new byte[array.length+1];
        for (int i=0;i<array.length;i++) { value[i]=(byte) array[i]; }
        value[value.length-1]=0;
    }
    @Override
    public int size() {
        return value.length+(new U16().size());
    }

    @Override
    public void read(ByteBuffer in) {
        U16 length=new U16();
        length.read(in);
        int len=((int)(length.value)) & 0xffff;
        value=new byte[len];
        for (int i=0;i<len;i++) {
            value[i]=in.get();
        }
    }

    @Override
    public void write(ByteBuffer out) {
        U16 length=new U16();
        length.value=(short) value.length;
        length.write(out);
        out.put(value);
    }

    @Override
    public String dump() {
        return "'"+toString()+"'";
    }
    public String toString() {
        String str="";
        for (byte b:value) {
            if (b>0) { str=str+(char)b; }
        }
        return str;
    }
        
}
