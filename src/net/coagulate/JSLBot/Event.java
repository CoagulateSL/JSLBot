/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.coagulate.JSLBot;

import java.io.IOException;
import static net.coagulate.JSLBot.Event.EVENTTYPE.*;
import static net.coagulate.JSLBot.Event.STATUS.*;


/**
 *
 * @author Iain
 */
public abstract class Event {

    public enum STATUS { UNSUBMITTED, IMMEDIATE, QUEUED, RUNNING, COMPLETE }
    private STATUS status=UNSUBMITTED;
    public STATUS status() { return status; }
    void setStatus (STATUS status) {
        this.status=status;
        if (Debug.TRACKCOMMANDS && type==COMMAND) { Log.debug(bot(),"Command "+getName()+" entering status "+status); }
    }
    
    public enum EVENTTYPE { UDP, XML, COMMAND };
    private EVENTTYPE type; public EVENTTYPE type() { return type; }
    public String typePrefix() { 
        if (type==UDP) { return "UDP"; }
        if (type==XML) { return "XML"; }
        if (type==COMMAND) { return "CMD"; }
        throw new IllegalArgumentException("How did you get here?  The constructor should have thrown this exception.");
    }
    
    private Regional r;
    public Regional region() { return r; }

    private JSLBot bot;
    public JSLBot bot() { return bot; }
    
    Event(JSLBot bot,Regional r) { this.bot=bot; this.r=r; setType(); }
    private void setType() {
        if (this instanceof UDPEvent) { type=UDP; return ;}
        if (this instanceof XMLEvent) { type=XML; return; }
        if (this instanceof CommandEvent) { type=COMMAND; return ;}
        throw new IllegalArgumentException("This class "+this.getClass().getName()+" is not assignable from the expected types");
    }
    
    public abstract String getName();
    public abstract String dump();
    
    public String getPrefixedName() {
        return typePrefix()+"/"+getName();
    }
    
    public void submit() throws IOException {
        bot().submit(this);
    }
    
}
