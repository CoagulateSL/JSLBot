package net.coagulate.JSLBot.Packets.Types;

import javax.annotation.Nonnull;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 *
 * @author Iain Price
 */
public class U32 extends Type {
    public int value;
    private ByteOrder byteorder=ByteOrder.LITTLE_ENDIAN;
    public U32() {}
    public U32(int v) { value=v; }
    public U32(int v,ByteOrder byteorder) { value=v; this.byteorder=byteorder; }

    @Override
    public int size() { return 4; }

    @Nonnull
    public ByteBuffer content() {
        @Nonnull ByteBuffer content=ByteBuffer.allocate(4);
        content.order(byteorder);
        content.putInt(value);
        return content;
    }

    public U32(@Nonnull ByteBuffer buffer) {
        readvalue(buffer);
    }
    public U32(@Nonnull ByteBuffer buffer, ByteOrder byteorder) {
        this.byteorder=byteorder;
        readvalue(buffer);
    }
    
    void readvalue(@Nonnull ByteBuffer buffer) {
        buffer.order(byteorder);
        value=buffer.getInt();
    }
    
    @Nonnull
    public String toString() { return Integer.toString(value); }

    @Override
    public void read(@Nonnull ByteBuffer in) {
        in.order(byteorder);
        value=in.getInt();
    }

    @Override
    public void write(@Nonnull ByteBuffer out) {
        out.order(byteorder);
        out.putInt(value);
    }

    @Nonnull
    @Override
    public String dump() {
        return Integer.toString(value);
    }
}
