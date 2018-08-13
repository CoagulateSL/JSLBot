package net.coagulate.JSLBot;

import java.io.IOException;
import java.util.Date;
import static net.coagulate.JSLBot.Event.EVENTTYPE.*;
import static net.coagulate.JSLBot.Event.STATUS.*;


/**
 *
 * @author Iain
 */
public abstract class Event {

    Object statusmonitor=new Object();
    /** Stages an event goes through */
    public enum STATUS { UNSUBMITTED, IMMEDIATE, QUEUED, RUNNING, COMPLETE }
    private STATUS status=UNSUBMITTED;
    public STATUS status() { return status; }
    void status (STATUS status) {
        synchronized(statusmonitor) {
            this.status=status;
            if (Debug.TRACKCOMMANDS && type==COMMAND) { Log.debug(bot(),"Command "+getName()+" entering status "+status); }
            statusmonitor.notify();
        }
    }
    
    /** Supported event types */
    public enum EVENTTYPE { UDP, XML, COMMAND };
    private EVENTTYPE type; public EVENTTYPE type() { return type; }
    public String typePrefix() { 
        if (type==UDP) { return "UDP"; }
        if (type==XML) { return "XML"; }
        if (type==COMMAND) { return "CMD"; }
        throw new IllegalArgumentException("How did you get here?  The constructor should have thrown this exception.");
    }
    
    private Regional r;
    /** Region this event originated from, if applicable */
    public Regional region() { return r; }

    private JSLBot bot;
    
    public JSLBot bot() { return bot; }
    
    Event(JSLBot bot,Regional r,String name) {
        this.bot=bot; this.r=r; this.name=name; setType();
        if (bot==null) { throw new IllegalArgumentException("Bot is mandatory"); }
        if (name==null || name.isEmpty()) { throw new IllegalArgumentException("Event name is mandatory"); }
        if (r==null) { throw new IllegalArgumentException("Region argument is mandatory, use bot.getRegion() if necessary"); }
    }
    
    private void setType() {
        if (this instanceof UDPEvent) { type=UDP; return ;}
        if (this instanceof XMLEvent) { type=XML; return; }
        if (this instanceof CommandEvent) { type=COMMAND; return ;}
        throw new IllegalArgumentException("This class "+this.getClass().getName()+" is not assignable from the expected types");
    }
    
    private String name;
    public final String getName() { return name; }
    public abstract String dump();
    
    public String getPrefixedName() {
        return typePrefix()+"/"+getName();
    }
    
    /** Submit event for full queue processing.
     * While commands choose IMMEDIATE/DELAYED mode, every other event passes through both and the handlers decide where to be installed.
     * @throws IOException 
     */
    public void submit() throws IOException {
        bot().submit(this);
    }

    public void waitFinish(long milliseconds) {
        long expire=new Date().getTime()+milliseconds;
        while ((new Date().getTime())<expire) {
            synchronized(statusmonitor) {
                try { statusmonitor.wait(1000); } catch (InterruptedException e) { }
            }
            if (status==COMPLETE) { return; }
        }
        throw new IllegalStateException("Command timed out in status "+status().toString());
    }
}
