package net.coagulate.JSLBot.Packets.Types;

import javax.annotation.Nonnull;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * @author Iain Price
 */
public class LLVector4 extends Unimplemented {
	public float x;
	public float y;
	public float z;
	public float t;
	
	@Override
	public void read(@Nonnull final ByteBuffer in) {
		in.order(ByteOrder.LITTLE_ENDIAN);
		x=in.getFloat();
		y=in.getFloat();
		z=in.getFloat();
		t=in.getFloat();
	}
	
	@Override
	public int size() {
		return 12;
	}
	
	@Override
	public void write(@Nonnull final ByteBuffer out) {
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
