package net.coagulate.JSLBot.Packets.Types;

import javax.annotation.Nonnull;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * @author Iain Price
 */
public class LLVector3 extends Type {
	
	public float x;
	public float y;
	public float z;
	public LLVector3(@Nonnull final ByteBuffer buffer) {
		read(buffer);
	}
	
	@Override
	public void read(@Nonnull final ByteBuffer in) {
		in.order(ByteOrder.LITTLE_ENDIAN);
		x=in.getFloat();
		y=in.getFloat();
		z=in.getFloat();
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
	}
	
	@Nonnull
	@Override
	public String dump() {
		return "<"+x+","+y+","+z+">";
	}
	
	public LLVector3(@Nonnull String pos) {
		pos=pos.replaceAll("<","");
		pos=pos.replaceAll(">","");
		@Nonnull final String[] comps=pos.split(",");
		x=Float.parseFloat(comps[0]);
		y=Float.parseFloat(comps[1]);
		z=Float.parseFloat(comps[2]);
	}
	
	public LLVector3() {
	}
	
	public LLVector3(final float x,final float y,final float z) {
		this.x=x;
		this.y=y;
		this.z=z;
	}
	
	public LLVector3(@Nonnull final String x,@Nonnull final String y,@Nonnull final String z) {
		this.x=Float.parseFloat(x);
		this.y=Float.parseFloat(y);
		this.z=Float.parseFloat(z);
	}
	
	@Nonnull
	public static LLVector3 random() {
		@Nonnull final LLVector3 v=new LLVector3();
		v.x=(float)(Math.random()*10.0-5.0);
		v.y=(float)(Math.random()*10.0-5.0);
		v.z=(float)(Math.random()*10.0-5.0);
		return v;
	}
	
}
