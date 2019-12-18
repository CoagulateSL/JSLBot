package net.coagulate.JSLBot;

import javax.annotation.Nonnull;
import java.util.logging.Logger;

/**
 * General template for a Handler and the various methods it may receive events through.
 *
 * @author Iain Prive
 */
public abstract class Handler {
	protected final Logger log;
	@Nonnull
	protected final JSLBot bot;
	protected Configuration config;

	public Handler(@Nonnull final JSLBot bot,
	               final Configuration config)
	{
		this.bot=bot;
		this.config=config;
		log=bot.getLogger("Handler."+getClass().getSimpleName());
	}

	/**
	 * Name of the handler
	 */
	@Nonnull
	@Override
	public String toString() { return bot+"/"+getClass().getSimpleName(); }

	/**
	 * Hook for post login activities
	 */
	public void loggedIn() {}

	/**
	 * Maintenance hook, called every few seconds if implemented
	 */
	public void maintenance() {}

}
