package net.coagulate.JSLBot;

import net.coagulate.JSLBot.Packets.Message;

import javax.annotation.Nonnull;

/**
 * Encapsulate a received UDP message packet as an Event.
 * There are some issues with trying to have packets extend events, since they once extended something else.
 *
 * @author Iain Price
 */
public class UDPEvent extends Event {
	private final Message content;

	public UDPEvent(@Nonnull final JSLBot bot,
	                @Nonnull final Regional r,
	                final Message content,
	                @Nonnull final String name) {
		super(bot,r,name);
		this.content=content;
	}

	public Message body() { return content; }

	@Override
	public String dump() {
		return body().dump();
	}
}
