/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.coagulate.JSLBot.Packets.Types;

import java.nio.ByteBuffer;

/**
 *
 * @author Iain
 */
public class U8 extends Type {
	public byte value=0;    
        
        public U8(){}
        public U8(int v) { value=(byte) (v & 0xff); }

        public U8(ByteBuffer rx) {
            read(rx);
        }
        @Override
        public String dump() { return Byte.toString(value); }
        @Override
        public void write(ByteBuffer out) { out.put(value); }
        @Override
        public void read(ByteBuffer in) { value=in.get(); }
        @Override
        public int size() { return 1; }
}
