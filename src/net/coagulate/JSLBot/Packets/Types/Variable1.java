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
public class Variable1 extends Type {


    public  byte[] value=new byte[0];

    public Variable1(){}
    public Variable1(String string) {
        char[] array=string.toCharArray();
        value=new byte[array.length+1];
        for (int i=0;i<array.length;i++) { value[i]=(byte) array[i]; }
        value[value.length-1]=0;
    }
    @Override
    public int size() {
        return value.length+1;
    }

    @Override
    public void read(ByteBuffer in) {
        U8 length=new U8();
        length.read(in);
        int len=((int)(length.value))&0xff;
        value=new byte[len];
        for (int i=0;i<len;i++) {
            value[i]=in.get();
        }
    }

    @Override
    public void write(ByteBuffer out) {
        U8 length=new U8();
        length.value=(byte)  value.length;
        length.write(out);
        out.put(value);
    }

    @Override
    public String dump() {
        return "'"+toString()+"'";
    }
    public String toString() {
        String str="";
        for (byte b:value) {
            if (b>0) { str=str+(char)b; }
        }
        return str;
    }
    
}
