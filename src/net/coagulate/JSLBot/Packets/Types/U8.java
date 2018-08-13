package net.coagulate.JSLBot.Packets.Types;

import java.nio.ByteBuffer;

/**
 *
 * @author Iain Price
 */
public class U8 extends Type {
    public byte value=0;    

    public U8(){}
    public U8(int v) { value=(byte) (v & 0xff); }

    public U8(ByteBuffer rx) {
        read(rx);
    }
    @Override
    public String dump() { return Byte.toString(value); }
    @Override
    public void write(ByteBuffer out) { out.put(value); }
    @Override
    public void read(ByteBuffer in) { value=in.get(); }
    @Override
    public int size() { return 1; }

    public int integer() {
        return ((int)value)&0xff;
    }
}
