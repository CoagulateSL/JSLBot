package net.coagulate.JSLBot.Packets;

import java.nio.ByteBuffer;

/** Represents the Message section of a UDP packet
 *
 * @author Iain Price
 */
public interface Message {

    public abstract int getId();
    public abstract int getFrequency();
    public abstract String getName();

    public abstract int size();
    public abstract String dump();
    public abstract void writeBytes(ByteBuffer out);
    public abstract void readBytes(ByteBuffer in);
    
}
