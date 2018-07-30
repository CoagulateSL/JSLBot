/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.coagulate.JSLBot.Packets;

import java.nio.ByteBuffer;

/**
 *
 * @author iain
 */
public interface Message {

    public abstract int getId();
    public abstract int getFrequency();
    public abstract String getName();

    public abstract int size();
    public abstract String dump();
    public abstract void writeBytes(ByteBuffer out);
    public abstract void readBytes(ByteBuffer in);
    
}
