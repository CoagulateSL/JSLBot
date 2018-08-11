/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.coagulate.JSLBot.Packets.Types;

import java.nio.ByteBuffer;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Iain
 */
public class LLUUID extends Type {
    byte[] uuid=new byte[16];
    public LLUUID() {}
    public LLUUID(String uuid) { 
        uuid=uuid.replaceAll("-","");
        if (uuid==null || uuid.length()==0) { uuid="00000000000000000000000000000000"; }
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
    public boolean equals(Object t) {
        if (!(t instanceof LLUUID)) { System.out.println("REALLY ODD COMPARISON TO A UUID"); return false; }
        LLUUID l=(LLUUID)t;
        for (int i=0;i<16;i++) { if (uuid[i]!=l.uuid[i]) { return false; }}
        return true;
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
}
