package net.coagulate.JSLBot.Handlers.Authorisation;

import net.coagulate.JSLBot.CommandEvent;
import net.coagulate.JSLBot.Configuration;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Log;

/**
 *
 * @author Iain Price
 */
public class AllowAll extends Authorisation {
    public AllowAll(JSLBot bot,Configuration c) {
        super(bot,c);
        Log.warn(bot,"Creating AllowAll authoriser, if used, anyone may completely control this bot");
    }
    public String approve(CommandEvent event) { return null; }  
}