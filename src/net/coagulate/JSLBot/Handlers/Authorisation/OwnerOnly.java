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
import net.coagulate.JSLBot.Packets.Types.LLUUID;

/**
 *
 * @author iain
 */
public class OwnerOnly extends Authorisation {
    private String ownerusername;
    private LLUUID owneruuid;
    public OwnerOnly(JSLBot bot,Configuration c) {
        super(bot,c);
        ownerusername=c.get("ownerusername");
        String owneruuidstr=c.get("owneruuid");
        owneruuid=null;
        if (owneruuidstr!=null) { owneruuid=new LLUUID(owneruuidstr); }
        if (ownerusername==null && owneruuid==null) { Log.error(bot,"OwnerAuth configured but no owner uuid or name was found, essentially in DenyAll mode"); return; }
        String permitted="";
        if (ownerusername!=null) { permitted="'"+ownerusername+"'"; }
        if (owneruuid!=null) { if (!permitted.isEmpty()) { permitted+=" "; } permitted+=owneruuid.toUUIDString(); }
        Log.info(bot,"OwnerOnly mode enabled, authorised for "+permitted);
    }
    public String approve(CommandEvent event) {
        if (owneruuid==null && ownerusername==null) { return "Owner authorisation configured but no owner set, essentially denying all"; }
        String invokerusername=event.invokerUsername();
        LLUUID invokeruuid=event.invokerUUID();
        if (invokeruuid==null && invokerusername==null) { return "No invoker in supplied command event"; }
        if (invokeruuid.equals(owneruuid)) { return null; } // approve
        if (invokerusername.equalsIgnoreCase(ownerusername)) { return null; }
        return "This bot is in owner only mode and you are not the owner recorded.";
    }
}