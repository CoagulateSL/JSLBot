package net.coagulate.JSLBot.Packets.Types;

import javax.annotation.Nonnull;
import java.nio.ByteBuffer;

/**
 *
 * @author Iain Price
 */
public abstract class Type {
    public Type() {
    }

    public Type(@Nonnull final ByteBuffer in) {
        this.read(in);
    }

    public abstract int size();
    public abstract void read(@Nonnull ByteBuffer in);
    public abstract void write(@Nonnull ByteBuffer out);
    public abstract String dump();
    public String toString() { return dump(); }
}
