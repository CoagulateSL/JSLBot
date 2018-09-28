package net.coagulate.JSLBot.Handlers.Authorisation;

import net.coagulate.JSLBot.CommandEvent;
import net.coagulate.JSLBot.Configuration;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Types.LLUUID;

/**
 *
 * @author Iain Price
 */
public class OwnerOnly extends Authorisation {
    private String ownerusername;
    private LLUUID owneruuid;
    /** Create an owner only authoriser.
     * 
     * @param bot The creating bot
     * @param c The configuration, from which "ownerusername" and "owneruuid" will be read.
     */
    public OwnerOnly(JSLBot bot,Configuration c) {
        super(bot,c);
        ownerusername=c.get("ownerusername");
        String owneruuidstr=c.get("owneruuid");
        owneruuid=null;
        if (owneruuidstr!=null) { owneruuid=new LLUUID(owneruuidstr); }
        if (ownerusername==null && owneruuid==null) { log.warning("OwnerAuth configured but no owner uuid or name was found, essentially in DenyAll mode"); return; }
        String permitted="";
        if (ownerusername!=null) { permitted="'"+ownerusername+"'"; }
        if (owneruuid!=null) { if (!permitted.isEmpty()) { permitted+=" "; } permitted+=owneruuid.toUUIDString(); }
        log.config("OwnerOnly mode enabled, authorised for "+permitted);
    }
    /** Approves events if issued by the owner.
     * Rejects all if no uuid + username defined.
     * Prefers to authorise by UUID but not all command event paths have this, will fall pack to the username match.
     * @param event The command event to inspect
     * @return Null if invoked by the owner and thus approved, otherwise a reason for rejection.
     */
    @Override
    public String approve(CommandEvent event) {
        if (owneruuid==null && ownerusername==null) { return "Owner authorisation configured but no owner set, essentially denying all"; }
        String invokerusername=event.invokerUsername();
        LLUUID invokeruuid=event.invokerUUID();
        if (invokeruuid==null && invokerusername==null) { return "No invoker in supplied command event"; }
        if (invokeruuid!=null && invokeruuid.equals(owneruuid)) { return null; } // approve
        if (invokerusername!=null && invokerusername.equalsIgnoreCase(ownerusername)) { return null; }
        return "This bot is in owner only mode and you are not the owner recorded.";
    }
}
