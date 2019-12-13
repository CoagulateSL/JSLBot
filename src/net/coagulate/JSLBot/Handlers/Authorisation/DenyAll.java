package net.coagulate.JSLBot.Handlers.Authorisation;

import net.coagulate.JSLBot.CommandEvent;
import net.coagulate.JSLBot.Configuration;
import net.coagulate.JSLBot.JSLBot;

import javax.annotation.Nonnull;

/** An implementation of Authorisation that denies everything.
 *
 * @author Iain Price
 */
public class DenyAll extends Authorisation {

    public DenyAll(@Nonnull JSLBot bot) { super(bot); }
    /** Create a DenyAll constructor.
     * Logged because this may be undesirable.
     *
     * @param bot The creating bot.
     * @param c The unused configuration
     */
    public DenyAll(@Nonnull JSLBot bot, Configuration c) {
        super(bot, c);
        log.config("Created a DenyAll authoriser, if engaged all remote commands will be denied.");
    }
    @Nonnull
    @Override
    /** Denies everything.
     * 
     * @param The event to not approve
     * @return A consistent rejection reason
     */
    public String approve(CommandEvent event) { return "The DenyAll authoriser denies all requests"; }
}
