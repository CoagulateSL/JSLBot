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
public class U16 extends Type {
    public short value;
    private ByteOrder byteorder=ByteOrder.LITTLE_ENDIAN;
    public U16() {}
    public U16(ByteOrder byteorder) { this.byteorder=byteorder; }
    public U16(short v) { value=v; }
    public U16(short v,ByteOrder byteorder) { value=v; this.byteorder=byteorder; }

    @Override
    public int size() { return 2; }

    public U16(ByteBuffer buffer) {
        readvalue(buffer);
    }
    public U16(ByteBuffer buffer,ByteOrder byteorder) {
        this.byteorder=byteorder;
        readvalue(buffer);
    }
    
    void readvalue(ByteBuffer buffer) {
        buffer.order(byteorder);
        value=buffer.getShort();
    }
    
    public String toString() { return Short.toString(value); }

    @Override
    public void read(ByteBuffer in) {
        in.order(byteorder);
        value=in.getShort();
    }

    @Override
    public void write(ByteBuffer out) {
        out.order(byteorder);
        out.putShort((short)value);
    }

    @Override
    public String dump() {
        return Short.toString(value);
    }    
}
