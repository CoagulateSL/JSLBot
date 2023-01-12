package net.coagulate.JSLBot.Packets.Types;

import javax.annotation.Nonnull;
import java.nio.ByteBuffer;

/**
 * @author Iain Price
 */
public abstract class Type {
	protected Type() {
	}
	
	protected Type(@Nonnull final ByteBuffer in) {
		this.read(in);
	}
	
	public abstract void read(@Nonnull ByteBuffer in);
	
	public abstract int size();
	
	public abstract void write(@Nonnull ByteBuffer out);
	
	public String toString() {
		return dump();
	}
	
	public abstract String dump();
}
