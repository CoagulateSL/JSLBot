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
public class AllowAll extends Authorisation {
    public AllowAll(Configuration c) { super(c); }
    public String approve(CommandEvent event) { return null; }  
}