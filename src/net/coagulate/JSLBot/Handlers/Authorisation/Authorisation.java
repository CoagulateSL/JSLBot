package net.coagulate.JSLBot.Handlers.Authorisation;

import java.util.logging.Logger;
import net.coagulate.JSLBot.CommandEvent;
import net.coagulate.JSLBot.Configuration;
import net.coagulate.JSLBot.JSLBot;

/** General contract for an authorisation plugin.
 *
 * @author Iain Price
 */
public abstract class Authorisation {
    protected final Logger log;
    public Authorisation(JSLBot bot,Configuration c){
        log=bot.getLogger("Authorisation."+this.getClass().getSimpleName());
    }
    /** Returns null if approved, otherwise some explanative text
     * @param event The CommandEvent to approve/reject
     * @return  Null if approved, otherwise a rejection reason.
     */
    public abstract String approve(CommandEvent event);
}
