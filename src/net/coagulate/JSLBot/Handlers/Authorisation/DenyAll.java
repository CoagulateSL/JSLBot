package net.coagulate.JSLBot.Handlers.Authorisation;

import net.coagulate.JSLBot.CommandEvent;
import net.coagulate.JSLBot.Configuration;
import net.coagulate.JSLBot.JSLBot;

import javax.annotation.Nonnull;

/**
 * An implementation of Authorisation that denies everything.
 *
 * @author Iain Price
 */
public class DenyAll extends Authorisation {

	public DenyAll(@Nonnull final JSLBot bot) { super(bot); }

	/**
	 * Create a DenyAll constructor.
	 * Logged because this may be undesirable.
	 *
	 * @param bot The creating bot.
	 * @param c   The unused configuration
	 */
	public DenyAll(@Nonnull final JSLBot bot,
	               final Configuration c) {
		super(bot,c);
		log.config("Created a DenyAll authoriser, if engaged all remote commands will be denied.");
	}

	// ---------- INSTANCE ----------

	/**
	 * Denies everything.
	 *
	 * @param event The event to not approve
	 *
	 * @return A consistent rejection reason
	 */
	@Nonnull
	@Override
	public String approve(@Nonnull final CommandEvent event) { return "The DenyAll authoriser denies all requests"; }
}
