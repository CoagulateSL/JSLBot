package net.coagulate.JSLBot.Handlers.Authorisation;

import net.coagulate.JSLBot.CommandEvent;
import net.coagulate.JSLBot.Configuration;
import net.coagulate.JSLBot.JSLBot;

/** General contract for an authorisation plugin.
 *
 * @author Iain Price
 */
public abstract class Authorisation {
    public Authorisation(JSLBot bot,Configuration c){}
    /** Returns null if approved, otherwise some explanative text
     * @param event The CommandEvent to approve/reject
     * @return  Null if approved, otherwise a rejection reason.
     */
    public abstract String approve(CommandEvent event);
}
