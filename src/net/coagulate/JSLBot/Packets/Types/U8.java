package net.coagulate.JSLBot.Packets.Types;

import javax.annotation.Nonnull;
import java.nio.ByteBuffer;

/**
 *
 * @author Iain Price
 */
public class U8 extends Type {
    public byte value=0;    

    public U8(){}
    public U8(int v) { value=(byte) (v & 0xff); }

    public U8(@Nonnull ByteBuffer rx) {
        read(rx);
    }
    @Nonnull
    @Override
    public String dump() { return Byte.toString(value); }
    @Override
    public void write(@Nonnull ByteBuffer out) { out.put(value); }
    @Override
    public void read(@Nonnull ByteBuffer in) { value=in.get(); }
    @Override
    public int size() { return 1; }

    public int integer() {
        return ((int)value)&0xff;
    }
}
