package net.coagulate.JSLBot.Packets.Types;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 *
 * @author Iain
 */
public class U16BE extends U16 {
    public U16BE() {super(ByteOrder.BIG_ENDIAN);}
    public U16BE(short a) { super(a,ByteOrder.BIG_ENDIAN); }
    public U16BE(ByteBuffer buffer) { super(buffer,ByteOrder.BIG_ENDIAN); }
}
