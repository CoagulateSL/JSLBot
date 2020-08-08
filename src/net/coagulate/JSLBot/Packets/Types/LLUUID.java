package net.coagulate.JSLBot.Packets.Types;

import javax.xml.bind.DatatypeConverter;
import java.nio.ByteBuffer;

/**
 *
 * @author Iain Price
 */
public final class LLUUID extends Type implements Comparable<LLUUID> {
    byte[] uuid=new byte[16];
    public LLUUID() {}
    public LLUUID(String uuid) {
        if (uuid==null || uuid.length()==0) { uuid="00000000000000000000000000000000"; }
        uuid=uuid.replaceAll("-","");
        if (uuid.length()!=32) { throw new IllegalArgumentException("UID should be 32 chars long: "+uuid); }
        for(int i=0;i<=15;i++) {
            this.uuid=DatatypeConverter.parseHexBinary(uuid);
        }
    }

    public LLUUID(ByteBuffer buffer) {
        read(buffer);
    }

    @Override
    public int size() { return 16; }

    public ByteBuffer content() {
        ByteBuffer content=ByteBuffer.allocate(size());
        content.put(uuid);
        return content;
    }
    
    @Override
    public String toString() {
        return DatatypeConverter.printHexBinary(uuid);
    }

    @Override
    public void read(ByteBuffer in) {
        uuid=new byte[16];
        in.get(uuid,0,16);
    }

    @Override
    public void write(ByteBuffer out) {
        out.put(uuid);
    }

    @Override
    public String dump() {
        return toString();
    }
    @Override
    public boolean equals(Object t) {
        if (!(t instanceof LLUUID)) { System.out.println("REALLY ODD COMPARISON TO A UUID"); return false; }
        LLUUID l=(LLUUID)t;
        for (int i=0;i<16;i++) { if (uuid[i]!=l.uuid[i]) { return false; }}
        return true;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(toLong());
    }

    public long toLong() {
        long value = 0;
        for (int i = 0; i < uuid.length; i++)
        {
           value += ((long) uuid[i] & 0xffL) << (8 * i);
        }
        return value;
    }

    public String toUUIDString() {
        String s=toString();
        return s.substring(0, 8)+"-"+s.substring(8,12)+"-"+s.substring(12,16)+"-"+s.substring(16,20)+"-"+s.substring(20);
    }

    @Override
    public int compareTo(LLUUID o) {
        if (o.toLong()==toLong()) { return 0; }
        if (o.toLong()>toLong()) { return 1; }
        if (o.toLong()<toLong()) { return -1; }
        throw new AssertionError("Code paths should be all complete at this point");
    }

}
