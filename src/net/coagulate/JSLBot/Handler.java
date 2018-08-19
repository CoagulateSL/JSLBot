package net.coagulate.JSLBot;

/** General template for a Handler and the various methods it may receive events through.
 *
 * @author Iain Prive
 */
public abstract class Handler {
    protected JSLBot bot;    
    protected Configuration config;
    
    public Handler(JSLBot bot,Configuration config){this.bot=bot; this.config=config;}
    @Override
    /** Name of the handler */
    public String toString() { return bot.toString()+"/"+this.getClass().getSimpleName(); }
    /** Hook for post login activities
     * @throws java.lang.Exception Throw any error for logging
     */
    public abstract void loggedIn() throws Exception;
 
    
    protected void debug(Event event,String message) { Log.debug((event==null?toString():event.toString()+"/"+getClass().getSimpleName()), message); }
    protected void info(Event event,String message) { Log.info((event==null?toString():event.toString()+"/"+getClass().getSimpleName()), message); }
    protected void note(Event event,String message) { Log.note((event==null?toString():event.toString()+"/"+getClass().getSimpleName()), message); }
    protected void warn(Event event,String message) { Log.warn((event==null?toString():event.toString()+"/"+getClass().getSimpleName()), message); }
    protected void error(Event event,String message) { Log.error((event==null?toString():event.toString()+"/"+getClass().getSimpleName()), message); }
    protected void crit(Event event,String message) { Log.crit((event==null?toString():event.toString()+"/"+getClass().getSimpleName()), message); }

    protected void debug(String message) { Log.debug(this,message); }
    protected void info(String message) { Log.info(this,message); }
    protected void note(String message) { Log.note(this,message); }
    protected void warn(String message) { Log.warn(this,message); }
    protected void error(String message) { Log.error(this,message); }
    protected void crit(String message) { Log.crit(this,message); }
}
