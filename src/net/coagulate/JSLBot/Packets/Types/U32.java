/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.coagulate.JSLBot.Packets.Types;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 *
 * @author Iain
 */
public class U32 extends Type {
    public int value;
    private ByteOrder byteorder=ByteOrder.LITTLE_ENDIAN;
    public U32() {}
    public U32(int v) { value=v; }
    public U32(int v,ByteOrder byteorder) { value=v; this.byteorder=byteorder; }

    @Override
    public int size() { return 4; }

    public ByteBuffer content() {
        ByteBuffer content=ByteBuffer.allocate(4);
        content.order(byteorder);
        content.putInt(value);
        return content;
    }

    public U32(ByteBuffer buffer) {
        readvalue(buffer);
    }
    public U32(ByteBuffer buffer,ByteOrder byteorder) {
        this.byteorder=byteorder;
        readvalue(buffer);
    }
    
    void readvalue(ByteBuffer buffer) {
        buffer.order(byteorder);
        value=buffer.getInt();
    }
    
    public String toString() { return Integer.toString(value); }

    @Override
    public void read(ByteBuffer in) {
        in.order(byteorder);
        value=in.getInt();
    }

    @Override
    public void write(ByteBuffer out) {
        out.order(byteorder);
        out.putInt(value);
    }

    @Override
    public String dump() {
        return Integer.toString(value);
    }
}
