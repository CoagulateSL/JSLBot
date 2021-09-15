package net.coagulate.JSLBot.Packets.Types;

import javax.annotation.Nonnull;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 *
 * @author Iain Price
 */
public class LLVector4 extends Unimplemented{
    public float x=0;
    public float y=0;
    public float z=0;
    public float t=0;
    @Override
    public int size() {
        return 12;
    }

    @Override
    public void read(@Nonnull ByteBuffer in) {
        in.order(ByteOrder.LITTLE_ENDIAN);
        x=in.getFloat();
        y=in.getFloat();
        z=in.getFloat();
        t=in.getFloat();
    }

    @Override
    public void write(@Nonnull ByteBuffer out) {
        out.order(ByteOrder.LITTLE_ENDIAN);
        out.putFloat(x);
        out.putFloat(y);
        out.putFloat(z);
        out.putFloat(t);
    }

    @Nonnull
    @Override
    public String dump() {
        return "<"+x+","+y+","+z+","+t+">";
    }
           
}
