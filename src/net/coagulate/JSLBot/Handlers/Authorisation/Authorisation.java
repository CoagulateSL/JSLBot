package net.coagulate.JSLBot.Handlers.Authorisation;

import net.coagulate.JSLBot.CommandEvent;
import net.coagulate.JSLBot.Configuration;
import net.coagulate.JSLBot.JSLBot;

/**
 *
 * @author Iain Price
 */
public abstract class Authorisation {
    public Authorisation(JSLBot bot,Configuration c){}
    /** Returns null if approved, otherwise some explanative text */
    public abstract String approve(CommandEvent event);
}
