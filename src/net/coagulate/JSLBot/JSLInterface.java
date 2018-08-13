package net.coagulate.JSLBot;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/** JSL Public "API" (the useful part that does all the work for you).
 *
 * @author Iain Price
 */
public class JSLInterface {
    private JSLBot bot;
    public JSLInterface(JSLBot bot) { this.bot=bot; }

    public void instantMessage(String uuid, String message) throws IOException {
        bot.waitConnection(15000);
        Map<String,String> cmd=new HashMap<>();
        cmd.put("uuid",uuid);
        cmd.put("message",message);
        new CommandEvent(bot, bot.getRegional(), "im", cmd, null).submitAndWait();
    }

    public void groupInvite(String avataruuid, String groupuuid) throws IOException {
        bot.waitConnection(15000);
        groupInvite(avataruuid,groupuuid,null);
    }
    
    public void groupInvite(String avataruuid, String groupuuid, String roleuuid) throws IOException {
        bot.waitConnection(15000);
        Map<String,String> cmd=new HashMap<>();
        cmd.put("avataruuid",avataruuid);
        cmd.put("groupuuid",groupuuid);
        cmd.put("roleuuid",roleuuid);
        new CommandEvent(bot, bot.getRegional(), "groups.invite", cmd, null).submitAndWait();
    }

    public void groupEject(String avataruuid, String groupuuid) throws IOException {
        bot.waitConnection(15000);
        Map<String,String> cmd=new HashMap<>();
        cmd.put("avataruuid",avataruuid);
        cmd.put("groupuuid",groupuuid);
        //cmd.put("roleuuid",roleuuid);
        new CommandEvent(bot, bot.getRegional(), "groups.eject", cmd, null).submitAndWait();
    }

    public void groupRoster(String groupuuid) throws IOException {
        bot.waitConnection(15000);
        Map<String,String> cmd=new HashMap<>();
        cmd.put("uuid",groupuuid);
        new CommandEvent(bot,bot.getRegional(),"groups.roster",cmd,null).submitAndWait();
    }


}
