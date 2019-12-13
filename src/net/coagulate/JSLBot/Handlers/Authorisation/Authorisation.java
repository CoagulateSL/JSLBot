package net.coagulate.JSLBot.Handlers.Authorisation;

import java.util.logging.Logger;
import net.coagulate.JSLBot.CommandEvent;
import net.coagulate.JSLBot.Configuration;
import net.coagulate.JSLBot.JSLBot;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/** General contract for an authorisation plugin.
 *
 * @author Iain Price
 */
public abstract class Authorisation {
    protected Logger log;
    public Authorisation(@Nonnull JSLBot bot, Configuration c){
        initLogger(bot);
    }

	protected Authorisation(JSLBot bot) { initLogger(bot); }

	private void initLogger(JSLBot bot) {log=bot.getLogger("Authorisation."+this.getClass().getSimpleName());}
	/** Returns null if approved, otherwise some explanative text
     * @param event The CommandEvent to approve/reject
     * @return  Null if approved, otherwise a rejection reason.
     */
    @Nullable
    public abstract String approve(CommandEvent event);
}
