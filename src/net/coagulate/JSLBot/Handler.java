package net.coagulate.JSLBot;

import javax.annotation.Nonnull;
import java.util.logging.Logger;

/** General template for a Handler and the various methods it may receive events through.
 *
 * @author Iain Prive
 */
public abstract class Handler {
    protected final Logger log;
    @Nonnull
    protected final JSLBot bot;
    protected Configuration config;
    
    public Handler(@Nonnull JSLBot bot, Configuration config){
        this.bot=bot;
        this.config=config;
        this.log=bot.getLogger("Handler."+this.getClass().getSimpleName());
    }
    @Nonnull
    @Override
    /** Name of the handler */
    public String toString() { return bot.toString()+"/"+this.getClass().getSimpleName(); }
    /** Hook for post login activities
     */
    public void loggedIn() {}
    
    /** Maintenance hook, called every few seconds if implemented
     *
     */
    public void maintenance() {}
    
}
