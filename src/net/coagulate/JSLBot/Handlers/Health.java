package net.coagulate.JSLBot.Handlers;

import net.coagulate.JSLBot.Configuration;
import net.coagulate.JSLBot.Handler;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Messages.HealthMessage;
import net.coagulate.JSLBot.UDPEvent;

import javax.annotation.Nonnull;

/**  Swallows health messages, and even stores the health.
 * @author Iain Price
 */
public class Health extends Handler {

    boolean verbose=false;
    float health=0;
    public Health(@Nonnull JSLBot bot, @Nonnull Configuration c) { super(bot,c); this.verbose=Boolean.parseBoolean(c.get("verbose","false")); }

    public void healthMessageUDPImmediate(@Nonnull UDPEvent event) {
        HealthMessage h=(HealthMessage) event.body();
        health=h.bhealthdata.vhealth.value;
        if (verbose) { log.info("Agent health is "+health); }
    }

}
