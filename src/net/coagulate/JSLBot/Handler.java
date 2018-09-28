package net.coagulate.JSLBot;

import java.util.logging.Logger;

/** General template for a Handler and the various methods it may receive events through.
 *
 * @author Iain Prive
 */
public abstract class Handler {
    protected final Logger log;
    protected JSLBot bot;    
    protected Configuration config;
    
    public Handler(JSLBot bot,Configuration config){
        this.bot=bot;
        this.config=config;
        this.log=bot.getLogger("Handler."+this.getClass().getSimpleName());
    }
    @Override
    /** Name of the handler */
    public String toString() { return bot.toString()+"/"+this.getClass().getSimpleName(); }
    /** Hook for post login activities
     * @throws java.lang.Exception Throw any error for logging
     */
    public void loggedIn() throws Exception {}
    
    /** Maintenance hook, called every few seconds if implemented
     * 
     * @throws Exception Any error during the maintenance.
     */
    public void maintenance() throws Exception {}
    
}
