package net.coagulate.JSLBot.Packets.Types;

import javax.annotation.Nonnull;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 *
 * @author Iain Price
 */
public class U64 extends Type {
    public long value=0;
    @Override
    public int size() {
        return 8;
    }

    public U64(){}
    public U64(@Nonnull String s) { value=Long.parseLong(s); }
    @Override
    public void read(@Nonnull ByteBuffer in) {
        in.order(ByteOrder.LITTLE_ENDIAN);
        value=in.getLong();
    }

    @Override
    public void write(@Nonnull ByteBuffer out) {
        out.order(ByteOrder.LITTLE_ENDIAN);
        out.putLong(value);
    }

    @Nonnull
    @Override
    public String dump() {
        return Long.toString(value);
    }
    
}
