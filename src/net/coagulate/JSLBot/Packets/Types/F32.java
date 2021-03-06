package net.coagulate.JSLBot.Packets.Types;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 *
 * @author Iain Price
 */
public class F32 extends Type{
    public float value=0;
    public F32() {}
    public F32(float v) { value=v; }
    @Override
    public int size() {
        return 4;
    }

    @Override
    public void read(ByteBuffer in) {
        in.order(ByteOrder.LITTLE_ENDIAN);
        value=in.getFloat();
    }

    @Override
    public void write(ByteBuffer out) {
        out.order(ByteOrder.LITTLE_ENDIAN);
        out.putFloat(value);
    }

    @Override
    public String dump() {
        return Float.toString(value);
    }
    
}
