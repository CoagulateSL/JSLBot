package net.coagulate.JSLBot.Handlers;

import net.coagulate.JSLBot.Configuration;
import net.coagulate.JSLBot.Handler;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Messages.HealthMessage;
import net.coagulate.JSLBot.UDPEvent;

import javax.annotation.Nonnull;

/**
 * Swallows health messages, and even stores the health.
 *
 * @author Iain Price
 */
public class Health extends Handler {
	
	final boolean verbose;
	float health;
	
	public Health(@Nonnull final JSLBot bot,@Nonnull final Configuration config) {
		super(bot,config);
		verbose=Boolean.parseBoolean(config.get("verbose","false"));
	}
	
	// ---------- INSTANCE ----------
	public void healthMessageUDPImmediate(@Nonnull final UDPEvent event) {
		@Nonnull final HealthMessage h=(HealthMessage)event.body();
		health=h.bhealthdata.vhealth.value;
		if (verbose) {
			log.info("Agent health is "+health);
		}
	}
	
}
