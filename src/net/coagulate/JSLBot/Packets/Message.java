package net.coagulate.JSLBot.Packets;

import java.nio.ByteBuffer;

/** Represents the Message section of a UDP packet
 *
 * @author Iain Price
 */
public interface Message {

    int getId();
    int getFrequency();
    String getName();

    int size();
    String dump();
    void writeBytes(ByteBuffer out);
    void readBytes(ByteBuffer in);
    
}
