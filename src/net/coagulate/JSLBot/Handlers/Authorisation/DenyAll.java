/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.coagulate.JSLBot.Handlers.Authorisation;

import net.coagulate.JSLBot.CommandEvent;
import net.coagulate.JSLBot.Configuration;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Log;

/**
 *
 * @author iain
 */
public class DenyAll extends Authorisation {

    public DenyAll(JSLBot bot, Configuration c) {
        super(bot, c);
        Log.note(bot,"Created a DenyAll authoriser, if engaged all remote commands will be denied.");
    }
    @Override
    public String approve(CommandEvent event) { return "The DenyAll authoriser denies all requests"; }
}
