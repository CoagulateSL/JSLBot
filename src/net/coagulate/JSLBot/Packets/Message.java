package net.coagulate.JSLBot.Packets;

import javax.annotation.Nonnull;
import java.nio.ByteBuffer;

/** Represents the Message section of a UDP packet
 *
 * @author Iain Price
 */
public interface Message {

    int getId();
    int getFrequency();
    @Nonnull
    String getName();

    int size();
    String dump();
    void writeBytes(@Nonnull ByteBuffer out);
    void readBytes(@Nonnull ByteBuffer in);
    
}
