package net.coagulate.JSLBot.Handlers.Authorisation;

import net.coagulate.JSLBot.CommandEvent;
import net.coagulate.JSLBot.Configuration;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Log;

/** An implementation of Authorisation that denies everything.
 *
 * @author Iain Price
 */
public class DenyAll extends Authorisation {

    /** Create a DenyAll constructor.
     * Logged because this may be undesirable.
     *
     * @param bot The creating bot.
     * @param c The unused configuration
     */
    public DenyAll(JSLBot bot, Configuration c) {
        super(bot, c);
        Log.note(bot,"Created a DenyAll authoriser, if engaged all remote commands will be denied.");
    }
    @Override
    /** Denies everything.
     * 
     * @param The event to not approve
     * @return A consistent rejection reason
     */
    public String approve(CommandEvent event) { return "The DenyAll authoriser denies all requests"; }
}
