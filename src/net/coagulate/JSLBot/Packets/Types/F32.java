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
public class F32 extends Type{
    public float value=0;
    @Override
    public int size() {
        return 4;
    }

    @Override
    public void read(ByteBuffer in) {
        in.order(ByteOrder.LITTLE_ENDIAN);
        value=in.getFloat();
    }

    @Override
    public void write(ByteBuffer out) {
        out.order(ByteOrder.LITTLE_ENDIAN);
        out.putFloat(value);
    }

    @Override
    public String dump() {
        return Float.toString(value);
    }
    
}
