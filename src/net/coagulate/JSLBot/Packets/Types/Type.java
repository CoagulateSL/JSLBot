package net.coagulate.JSLBot.Packets.Types;

import java.nio.ByteBuffer;

/**
 *
 * @author Iain Price
 */
public abstract class Type {
    public Type() {}
    public Type(ByteBuffer in) { 
        this.read(in);
    }
    public abstract int size();
    public abstract void read(ByteBuffer in);
    public abstract void write(ByteBuffer out);
    public abstract String dump();
    public String toString() { return dump(); }
}
