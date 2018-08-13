package net.coagulate.JSLBot.Packets;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.coagulate.JSLBot.Circuit;
import net.coagulate.JSLBot.Debug;
import net.coagulate.JSLBot.Event;
import net.coagulate.JSLBot.Packets.Types.Type;
import net.coagulate.JSLBot.Packets.Types.U32BE;
import net.coagulate.JSLBot.Packets.Types.U32;
import net.coagulate.JSLBot.Packets.Types.U8;
import net.coagulate.JSLBot.Packets.Messages.Lookup;
import net.coagulate.JSLBot.Packets.Types.U16BE;

/**
 *
 * @author Iain
 */
public class Packet {
    byte flags;
    int sequence=0;
    public int getSequence() { return sequence; }
    public void setZeroCode(boolean setto) {
        if (setto)
        { flags=(byte) (flags|((byte)0x80)); }
        else
        { flags=(byte) (flags & ((byte)(~0x80))); }
    }
    public void setReliable(boolean setto) {
        if (setto)
        { flags=(byte) (flags|((byte)0x40)); }
        else
        { flags=(byte) (flags & ((byte)(~0x40))); }
    }
    public void setResent(boolean setto) {
        if (setto)
        { flags=(byte) (flags|((byte)0x20)); }
        else
        { flags=(byte) (flags & ((byte)(~0x20))); }
    }
    public void setAck(boolean setto) {
        if (setto)
        { flags=(byte) (flags|((byte)0x10)); }
        else
        { flags=(byte) (flags & ((byte)(~0x10))); }
    }
    public boolean getZeroCode() { if ((flags & 0x80)!=0) { return true; } return false; }
    public boolean getReliable() { if ((flags & 0x40)!=0) { return true; } return false; }
    public boolean getResent() { if ((flags & 0x20)!=0) { return true; } return false; }
    public boolean getAck() { if ((flags & 0x10)!=0) { return true; } return false; }
    public Packet(Message m) { message=m; }
    private Message message;
    public Message message() { return message; }
    //public abstract int code();
    //public abstract int codesize();
    public void write(Circuit c,ByteBuffer out) {
        // note this method might be called on a packet more than once (for retransmission), some things only should happen the first time, like:
        if (sequence==0) { 
            // sequence number allocation
            sequence=c.getSequence();
            // registering for reliable transmission
            if (getReliable()) { c.requiresAck(this); }
        }
        else
        {
            // and if we're not the first attempt, well, by the book:
            setResent(true);
        }
        out.order(ByteOrder.BIG_ENDIAN);  // so says the documentation :/
        out.put((byte)flags);
        out.putInt(sequence); // sequence number
        out.put((byte)0); // extra  header byte count
        switch (getFrequency()) {
            case Frequency.HIGH:
                (new U8(getId())).write(out); break;
            case Frequency.MEDIUM:
                (new U16BE((short)(0xff00+getId()))).write(out); break;
            case Frequency.LOW:
                (new U32BE(0xffff0000+getId())).write(out); break;
            case Frequency.FIXED:
                (new U32BE(getId())).write(out); break;
            default: throw new IllegalArgumentException("Frequency invalid "+getFrequency());
        }
        message().writeBytes(out);
    }


    public static Packet decode(ByteBuffer source) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        byte flags=source.get();
        source.order(ByteOrder.BIG_ENDIAN);
        int sequence=source.getInt();
        byte extralen=source.get();
        byte extra[]=new byte[extralen];
        if (extralen>0) { source.get(extra,0,extralen); }

        // STOP - everything from here on in MIGHT be zerocoded
        if ((flags & 0x80)!=0) {
            // okay well this is a bit garbage :P
            List<Byte> uncoded=new ArrayList<Byte>();
            // zero run length encoding.  a zero byte is followed by a "qty" byte, how many zeros there were.  e.g. 00 01 means 1 zero :P
            // all other bytes are preserved.
            int pos=source.position();
            byte lastbyte=source.get(source.capacity()-1);
            source.position(pos);
            int bodylength=source.capacity()-pos;
            // ACKS are NOT NOT NOT ZERO CODED.
            // only way to deal with this, since we dont have a body length marker, is to rely on the ack flag
            // acks are 4 byte acks followed by 1 byte quantity so...
            // we have to read the last byte and work out tbe body length based on that, zero decode that, and keep everything else verbatim...
            // this stuff actually works though.
            boolean acks=false;
            if ((flags & 0x10)!=0) { acks=true; }
            //System.out.println("Last byte is "+lastbyte+", body length is "+bodylength+" acks is "+acks);
            if (acks) { bodylength-=1; bodylength-=(4*lastbyte); }
            while (bodylength>0) {
                bodylength--;
                byte b=source.get();
                if (b!=0) {
                    uncoded.add(b);
                } else {
                    bodylength--;
                    int qty=source.get()&0xff;
                    for (int i=0;i<qty;i++) { uncoded.add((byte)0); }
                }
            }
            while (source.hasRemaining()) { byte b=source.get(); uncoded.add(b); }
            source=ByteBuffer.allocate(uncoded.size());
            for (Byte b:uncoded) { source.put(b); }
            source.position(0);
        }
        
        
        // 1 2 or 4 byte message ID.  joy
        int code=0;
        int codesize=0;
        int codebyte=Byte.toUnsignedInt(source.get());
        // basically:
        // 01-FE - 1 byte message codes
        // FF 01-FE 2 byte codes
        // FF FF 0001-FFFF 4 byte codes
        if (codebyte==0xff) {
            // 2 or 4 byte code
            codebyte=Byte.toUnsignedInt(source.get());
            if (codebyte==0xff) {
                codesize=4;
                codebyte=Byte.toUnsignedInt(source.get());
                code=codebyte<<8;
                codebyte=Byte.toUnsignedInt(source.get());
                code=code | codebyte;
                code=code | 0xffff0000;

            } else {
                codesize=2;
                code=codebyte; // 1 byte ff, 1 byte code
                code=code | 0xff00;
            }                
        }
        else
        {
            codesize=1;
            code=(int)codebyte & 0xff;
        }
        if (Debug.PACKET) { System.out.println("Message is "+codebyte+" "+Integer.toHexString(code)+" (len:"+codesize+")");}
        String messagetype=Lookup.lookup(code);
        //System.out.println("Message type is "+messagetype);
        if (messagetype.equals("TestMessage")) { // seems unlikely, debug it ...
            System.err.println("Decoded a message to TestMessage - decoder error???");
            byte[] array=source.array();
            for (int i=0;i<array.length;i++) {
                System.err.print(Integer.toHexString(Byte.toUnsignedInt(array[i]))+" ");
                if (i % 16 == 0) { System.out.println(); }
            }
            // this occured once, because I hadn't written the zerocoding decoder ;)  
        }
        Packet response=null;
        Message message=null;
        // fun with reflection =)
        Class messageclass;
        try {
            messageclass=Class.forName("net.coagulate.JSLBot.Packets.Messages."+messagetype);
        }
        catch (ClassNotFoundException e) {
            return null;
        }
        Constructor messageconstructor=messageclass.getConstructor(new Class[0]);
        message=(Message) messageconstructor.newInstance(new Object[0]);
        response=new Packet(message);
        if (response==null) { return null; }
        response.flags=flags;
        response.sequence=sequence;
        response.message().readBytes(source);
        //System.out.println(source.position()+"/"+source.capacity()+" (ZC:"+response.getZeroCode());
        // appended acks? :P
        if (response.getAck()) {
            int remain=source.capacity()-source.position();
            remain--;
            //if (remain>0) { System.out.println("Appended ack bytes corrected "+remain); }
            remain=remain/4;
            for (int i=0;i<remain;i++) {
                U32BE ack=new U32BE(source);
                //System.out.println("READ ACK "+ack);
                response.appendedacks.add(ack.value);
            }
        }
        return response;
    }
    public Set<Integer> appendedacks=new HashSet<>();
    
    public String dump() {
        String acks="";
        if (!appendedacks.isEmpty()) {
            acks=" [ACK:";
            String comma="";
            for (Integer i:appendedacks) {
                acks=acks+comma+i;
                comma=",";
            }
            acks+="]";
        }
        String dump=getName()+":";
        if (this.getZeroCode()) { dump+="[Zero] "; }
        if (this.getReliable()) { dump+="[Reliable] "; }
        if (this.getResent()) { dump+="[Resent] "; }
        if (this.getAck()) { dump+="[Acks] "; }
        dump+="#"+sequence+""+messageDump();
        return dump+acks;
    }
    

    public int size() {
        int total=messageSize();
        //System.out.println("message size "+total);
        total+=6; // flags, seq, zerobyte 'extra length' :P
        if (getFrequency()==Frequency.HIGH) { total=total+1; }
        if (getFrequency()==Frequency.MEDIUM) { total=total+2; }
        if (getFrequency()==Frequency.LOW || getFrequency()==Frequency.FIXED) { total=total+4; }
        //System.out.println("final message size "+total);
        return total;
    }
    
    public int getId() { return message().getId(); }
    public int getFrequency() { return message().getFrequency(); }
    public String getName() { return message().getName(); }
    
    public String toString() { return dump(); }

    public int messageSize() { return message().size(); }


    public String messageDump() { return message().dump(); }

    public byte getFlags() {
        return flags;
    }

}
