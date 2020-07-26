package net.coagulate.JSLBot.Packets.Types;

import java.nio.ByteBuffer;
import net.coagulate.JSLBot.BotUtils;

/**
 *
 * @author Iain Price
 */
public class Fixed4 extends Unimplemented{

    public final byte[] values =new byte[4];
    @Override
    public int size() {
        return 4;
    }

    @Override
    public void read(ByteBuffer in) {
        in.get(values,0,4);
    }

    @Override
    public void write(ByteBuffer out) {
        out.put(values,0,4);
    }

    @Override
    public String dump() {
        return BotUtils.hex(values);
    }
            
}
