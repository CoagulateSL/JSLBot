package net.coagulate.JSLBot.Handlers.Authorisation;

import net.coagulate.JSLBot.CommandEvent;
import net.coagulate.JSLBot.Configuration;
import net.coagulate.JSLBot.JSLBot;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Allow All implementation of Authorisation
 *
 * @author Iain Price
 */
public class AllowAll extends Authorisation {
	/**
	 * Create an AllowAll authoriser, that permits everything.
	 * Approves everything.  Not recommnded.
	 *
	 * @param bot The creating bot
	 * @param c   Configuration (unused)
	 */
	public AllowAll(@Nonnull final JSLBot bot,
	                final Configuration c) {
		super(bot,c);
		log.warning("Creating AllowAll authoriser, if used, anyone may completely control this bot");
	}

	// ---------- INSTANCE ----------

	/**
	 * Approve all events
	 *
	 * @param event Event to approve
	 *
	 * @return The null string, which approves everything
	 */
	@Nullable
	@Override
	public String approve(@Nonnull final CommandEvent event) { return null; }
}
