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
public class AllowAll extends Authorisation {
    public AllowAll(JSLBot bot,Configuration c) {
        super(bot,c);
        Log.warn(bot,"Creating AllowAll authoriser, if used, anyone may completely control this bot");
    }
    public String approve(CommandEvent event) { return null; }  
}