package net.coagulate.JSLBot.Packets.Types;

import javax.annotation.Nonnull;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * @author Iain Price
 */
public class U16 extends Type {
    public short value;
    private ByteOrder byteorder = ByteOrder.LITTLE_ENDIAN;

    public U16() {
    }

    public U16(final ByteOrder byteorder) {
        this.byteorder = byteorder;
    }

    public U16(final short v) {
        value = v;
    }

    public U16(final short v, final ByteOrder byteorder) {
        value = v;
        this.byteorder = byteorder;
    }

    @Override
    public int size() {
        return 2;
    }

    public U16(@Nonnull final ByteBuffer buffer) {
        readvalue(buffer);
    }

    public U16(@Nonnull final ByteBuffer buffer, final ByteOrder byteorder) {
        this.byteorder = byteorder;
        readvalue(buffer);
    }
    
    void readvalue(@Nonnull final ByteBuffer buffer) {
        buffer.order(byteorder);
        value = buffer.getShort();
    }

    @Nonnull
    public String toString() { return Short.toString(value); }

    @Override
    public void read(@Nonnull final ByteBuffer in) {
        in.order(byteorder);
        value = in.getShort();
    }

    @Override
    public void write(@Nonnull final ByteBuffer out) {
        out.order(byteorder);
        out.putShort(value);
    }

    @Nonnull
    @Override
    public String dump() {
        return Short.toString(value);
    }    
}
