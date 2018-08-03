/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.coagulate.JSLBot.Handlers.Authorisation;

import net.coagulate.JSLBot.CommandEvent;
import net.coagulate.JSLBot.Configuration;

/**
 *
 * @author iain
 */
public class DenyAll extends Authorisation {

    public DenyAll(Configuration c) {super(c);}
    @Override
    public String approve(CommandEvent event) { return "The DenyAll authoriser denies all requests"; }
}
