package net.coagulate.JSLBot.Packets.Types;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 *
 * @author Iain Price
 */
public class U32BE extends U32 {
    public U32BE(int a) { super(a,ByteOrder.BIG_ENDIAN); }
    public U32BE(ByteBuffer buffer) { super(buffer,ByteOrder.BIG_ENDIAN); }
}
