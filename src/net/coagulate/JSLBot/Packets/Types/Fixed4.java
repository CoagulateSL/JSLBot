package net.coagulate.JSLBot.Packets.Types;

import net.coagulate.JSLBot.BotUtils;

import javax.annotation.Nonnull;
import java.nio.ByteBuffer;

/**
 * @author Iain Price
 */
public class Fixed4 extends Unimplemented {
	
	public final byte[] values=new byte[4];
	
	@Override
	public void read(@Nonnull final ByteBuffer in) {
		in.get(values,0,4);
	}
	
	@Override
	
	public int size() {
		return 4;
	}
	
	@Override
	public void write(@Nonnull final ByteBuffer out) {
		out.put(values,0,4);
	}
	
	@Nonnull
	@Override
	public String dump() {
		return BotUtils.hex(values);
	}
	
}
