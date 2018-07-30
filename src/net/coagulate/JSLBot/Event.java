/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.coagulate.JSLBot;

import net.coagulate.JSLBot.LLSD.Atomic;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Packet;

/**
 *
 * @author Iain
 */
public class Event {
    private Message m=null;
    private Atomic body=null;

    public Event(String string, Atomic body) { name=string; this.body=body; }
    public Event(Packet p,Regional region) {m=p.message(); this.regional=region; name=m.getName();}
    public Event(Message m,Regional region) { this.m=m; this.regional=region; name=m.getName(); }

    public Atomic body() { return body; }
    public Message message() { return m; }

    private String name; public String getName() { return name; }
    Regional regional=null; public Regional getRegion() { return regional; }

    public String dump() {
        if (m!=null) { return m.dump(); }
        if (body()!=null) { return body.toXML(); }
        return "UNKNOWN EVENT TYPE";
    }
}
