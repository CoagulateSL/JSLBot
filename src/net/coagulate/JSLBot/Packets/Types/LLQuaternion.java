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
public class LLQuaternion extends Type {
    public float x=0;
    public float y=0;
    public float z=0;
    @Override
    public int size() {
        return 12;
    }

    @Override
    public void read(ByteBuffer in) {
        in.order(ByteOrder.LITTLE_ENDIAN);
        x=in.getFloat();
        y=in.getFloat();
        z=in.getFloat();
    }

    @Override
    public void write(ByteBuffer out) {
        out.order(ByteOrder.LITTLE_ENDIAN);
        out.putFloat(x);
        out.putFloat(y);
        out.putFloat(z);
    }

    @Override
    public String dump() {
        return "<"+x+","+y+","+z+">";
    }
    
}
