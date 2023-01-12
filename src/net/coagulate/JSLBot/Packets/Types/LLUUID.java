package net.coagulate.JSLBot.Packets.Types;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.xml.bind.DatatypeConverter;
import java.nio.ByteBuffer;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Iain Price
 */
public final class LLUUID extends Type implements Comparable<LLUUID> {
	byte[] uuid=new byte[16];
	
	public LLUUID() {
	}
	
	public LLUUID(@Nullable String uuid) {
		if (uuid==null||uuid.isEmpty()) {
			uuid="00000000000000000000000000000000";
		}
		uuid=uuid.replaceAll("-","");
		if (uuid.length()!=32) {
			throw new IllegalArgumentException("UID should be 32 chars long: "+uuid);
		}
		for (int i=0;i<=15;i++) {
			this.uuid=DatatypeConverter.parseHexBinary(uuid);
		}
	}
	
	public LLUUID(@Nonnull final ByteBuffer buffer) {
		read(buffer);
	}
	
	@Override
	public void read(@Nonnull final ByteBuffer in) {
		uuid=new byte[16];
		in.get(uuid,0,16);
	}
	
	@Override
	public int size() {
		return 16;
	}
	
	@Override
	public void write(@Nonnull final ByteBuffer out) {
		out.put(uuid);
	}
	
	@Override
	public String toString() {
		return DatatypeConverter.printHexBinary(uuid);
	}
	
	@Override
	public String dump() {
		return toString();
	}
	
	@Nonnull
	public static LLUUID random() {
		// apparently UUIDs might be used as arbitary request markers.
		@Nonnull final StringBuilder random=new StringBuilder();
		for (int i=0;i<32;i++) {
			random.append(randomHexChar());
		}
		return new LLUUID(random.toString());
	}
	
	@Nonnull
	private static String randomHexChar() {
		final int rand=ThreadLocalRandom.current().nextInt(16);
		if (rand<10) {
			return String.valueOf(rand);
		}
		// lazy
		if (rand==10) {
			return "A";
		}
		if (rand==11) {
			return "B";
		}
		if (rand==12) {
			return "C";
		}
		if (rand==13) {
			return "D";
		}
		if (rand==14) {
			return "E";
		}
		if (rand==15) {
			return "F";
		}
		throw new AssertionError("A random from 0-15 was outside 0-15 : "+rand);
	}
	
	@Nonnull
	public ByteBuffer content() {
		@Nonnull final ByteBuffer content=ByteBuffer.allocate(size());
		content.put(uuid);
		return content;
	}
	
	@Override
	public int hashCode() {
		return Long.hashCode(toLong());
	}
	
	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof @Nonnull final LLUUID l)) {
			System.out.println("REALLY ODD COMPARISON TO A UUID");
			return false;
		}
		for (int i=0;i<16;i++) {
			if (uuid[i]!=l.uuid[i]) {
				return false;
			}
		}
		return true;
	}
	
	public long toLong() {
		long value=0;
		for (int i=0;i<uuid.length;i++) {
			value+=(uuid[i]&0xffL)<<(8*i);
		}
		return value;
	}
	
	@Nonnull
	public String toUUIDString() {
		final String s=toString();
		return s.substring(0,8)+"-"+s.substring(8,12)+"-"+s.substring(12,16)+"-"+s.substring(16,20)+"-"+s.substring(20);
	}
	
	@Override
	public int compareTo(@Nonnull final LLUUID o) {
		if (o.toLong()==toLong()) {
			return 0;
		}
		if (o.toLong()>toLong()) {
			return 1;
		}
		if (o.toLong()<toLong()) {
			return -1;
		}
		throw new AssertionError("Code paths should be all complete at this point");
	}
	
}
