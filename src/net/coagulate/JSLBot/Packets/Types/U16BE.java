package net.coagulate.JSLBot.Packets.Types;

import javax.annotation.Nonnull;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * @author Iain Price
 */
public class U16BE extends U16 {
    public U16BE() {
        super(ByteOrder.BIG_ENDIAN);
    }

    public U16BE(final short a) {
        super(a, ByteOrder.BIG_ENDIAN);
    }

    public U16BE(@Nonnull final ByteBuffer buffer) {
        super(buffer, ByteOrder.BIG_ENDIAN);
    }
}
