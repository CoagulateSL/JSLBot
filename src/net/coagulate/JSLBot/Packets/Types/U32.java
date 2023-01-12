package net.coagulate.JSLBot.Packets.Types;

import javax.annotation.Nonnull;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * @author Iain Price
 */
public class U32 extends Type {
	public  int       value;
	private ByteOrder byteorder=ByteOrder.LITTLE_ENDIAN;
	
	public U32() {
	}
	
	public U32(final int value) {
		this.value=value;
	}
	
	public U32(final int value,final ByteOrder byteorder) {
		this.value=value;
		this.byteorder=byteorder;
	}
	
	public U32(@Nonnull final ByteBuffer buffer) {
		readvalue(buffer);
	}
	
	void readvalue(@Nonnull final ByteBuffer buffer) {
		buffer.order(byteorder);
		value=buffer.getInt();
	}
	
	public U32(@Nonnull final ByteBuffer buffer,final ByteOrder byteorder) {
		this.byteorder=byteorder;
		readvalue(buffer);
	}
	
	@Nonnull
	public ByteBuffer content() {
		@Nonnull final ByteBuffer content=ByteBuffer.allocate(4);
		content.order(byteorder);
		content.putInt(value);
		return content;
	}
	
	@Override
	public void read(@Nonnull final ByteBuffer in) {
		in.order(byteorder);
		value=in.getInt();
	}
	
	@Override
	public int size() {
		return 4;
	}
	
	@Override
	public void write(@Nonnull final ByteBuffer out) {
		out.order(byteorder);
		out.putInt(value);
	}
	
	@Nonnull
	public String toString() {
		return Integer.toString(value);
	}
	
	@Nonnull
	@Override
	public String dump() {
		return Integer.toString(value);
	}
}
