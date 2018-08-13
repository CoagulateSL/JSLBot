package net.coagulate.JSLBot;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/** JSL Public "API" (the useful part that does all the work for you).
 *
 * @author Iain Price <git@predestined.net>
 */
public class JSLInterface {
    private JSLBot bot;
    public JSLInterface(JSLBot bot) { this.bot=bot; }

    public void instantMessage(String uuid, String message) throws IOException {
        Map<String,String> cmd=new HashMap<>();
        cmd.put("uuid",uuid);
        cmd.put("message",message);
        new CommandEvent(bot, bot.getRegional(), "im", cmd, null).submitAndWait();
    }

    public void groupInvite(String avataruuid, String groupuuid) throws IOException {
        groupInvite(avataruuid,groupuuid,null);
    }
    
    public void groupInvite(String avataruuid, String groupuuid, String roleuuid) throws IOException {
        Map<String,String> cmd=new HashMap<>();
        cmd.put("avataruuid",avataruuid);
        cmd.put("groupuuid",groupuuid);
        cmd.put("roleuuid",roleuuid);
        new CommandEvent(bot, bot.getRegional(), "groups.invite", cmd, null).submitAndWait();
    }

    public void groupEject(String avataruuid, String groupuuid) throws IOException {
        Map<String,String> cmd=new HashMap<>();
        cmd.put("avataruuid",avataruuid);
        cmd.put("groupuuid",groupuuid);
        //cmd.put("roleuuid",roleuuid);
        new CommandEvent(bot, bot.getRegional(), "groups.eject", cmd, null).submitAndWait();
    }


}
