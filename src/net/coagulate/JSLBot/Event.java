package net.coagulate.JSLBot;

import java.util.Date;
import static net.coagulate.JSLBot.Event.EVENTTYPE.*;
import static net.coagulate.JSLBot.Event.STATUS.*;


/**  Abstract superclass of different types of event we propagate.
 *
 * @author Iain Price
 */
public abstract class Event {

    // locking the status field, command event monitors this
    final Object statusmonitor=new Object();
    /** Stages an event goes through */
    public enum STATUS { UNSUBMITTED, IMMEDIATE, QUEUED, RUNNING, COMPLETE }
    private STATUS status=UNSUBMITTED;
    /** Get the event's current status
     * 
     * @return The STATUS of this event
     */
    public STATUS status() { return status; }
    /** Set the event's current status
     * 
     * @param status 
     */
    void status (STATUS status) {
        synchronized(statusmonitor) {
            this.status=status;
            if (Debug.TRACKCOMMANDS && type==COMMAND) { Log.debug(r,"Command "+getName()+" entering status "+status); }
            statusmonitor.notify();
        }
    }
    
    /** Supported event types */
    public enum EVENTTYPE { UDP, XML, COMMAND };
    private EVENTTYPE type;
    /** Get the events type
     * 
     * @return The EVENTTYPE of this event
     */
    public EVENTTYPE type() { return type; }
    /** Returns the type as a string
     * 
     * @return The type of this event, UDP, XML or Command, as a string
     */
    public String typeString() { 
        if (type==UDP) { return "UDP"; }
        if (type==XML) { return "XML"; }
        if (type==COMMAND) { return "Command"; }
        throw new AssertionError("Unknown type.  How did you get here?  The constructor should have thrown this exception.");
    }
    
    private final Regional r;
    /** Region this event originated from, if applicable
     * @return Regional data for the originating region
     */
    public Regional region() { return r; }

    private final JSLBot bot;
    
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
    
    @Override
    public String toString() {
        return r.toString()+"/"+getPrefixedName();
    }
    private final String name;
    public final String getName() { return name; }
    public abstract String dump();
    
    public String getPrefixedName() {
        return typeString()+"/"+getName();
    }
    
    /** Submit event for full queue processing.
     * While commands choose IMMEDIATE/DELAYED mode, every other event passes through both and the handlers decide where to be installed.
     */
    public void submit() {
        bot().brain().execute(this);
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
