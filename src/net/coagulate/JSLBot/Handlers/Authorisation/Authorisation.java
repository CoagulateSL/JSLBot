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
public abstract class Authorisation {
    public Authorisation(Configuration c){}
    /** Returns null if approved, otherwise some explanative text */
    public abstract String approve(CommandEvent event);
}
