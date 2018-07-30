package net.coagulate.JSLBot.Handlers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.coagulate.JSLBot.Configuration;
import net.coagulate.JSLBot.Event;
import net.coagulate.JSLBot.Handler;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.LLSD.Atomic;
import net.coagulate.JSLBot.LLSD.LLSDArray;
import net.coagulate.JSLBot.LLSD.LLSDBinary;
import net.coagulate.JSLBot.LLSD.LLSDBoolean;
import net.coagulate.JSLBot.LLSD.LLSDInteger;
import net.coagulate.JSLBot.LLSD.LLSDMap;
import net.coagulate.JSLBot.LLSD.LLSDString;
import net.coagulate.JSLBot.LLSD.LLSDUUID;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Regional;

/** Handle group related activities and messages.
 *
 * @author Iain Price <git@predestined.net>
 */
public class Groups extends Handler {

    public Groups(Configuration config) {
        super(config);
    }

    @Override
    public String toString() { return "Manages groups"; }

    
    JSLBot bot=null;
    @Override
    public void initialise(JSLBot ai) throws Exception { 
        bot=ai;
        bot.addHandler("XML_AgentGroupDataUpdate", this);
        bot.addCommand("groups.list",this);
    }

    @Override
    public void processImmediate(Event p) throws Exception {
    }

    @Override
    public void process(Event p) throws Exception {
        if (p.getName().equals("XML_AgentGroupDataUpdate")) { agentGroupDataUpdate((LLSDMap) p.body(),p.getRegion()); }
    }

    @Override
    public String execute(String command, Map<String, String> parameters) throws Exception {
        if (command.equals("groups.list")) {
            String resp="Groups:";
            synchronized(groups) {
                for(GroupData g:groups.values()) {
                    resp+="\n";
                    resp+=g.groupname+" ("+g.uuid.toUUIDString()+") ";
                    if (g.contribution!=0) { resp+="Contribution:"+g.contribution+" "; }
                    if (g.listinprofile) { resp+="Listed"; } else { resp+="Unlisted"; }
                }
            }
            return resp;
        }
        return "Unknown command";
    }

    @Override
    public void loggedIn() throws Exception {
    }

    @Override
    public String help(String command) {
        return "Unknown command";
    }

    private void agentGroupDataUpdate(LLSDMap body, Regional region) {
        LLSDArray groupslist=(LLSDArray) body.get("GroupData");
        synchronized(groups) {
            for (Iterator it = groupslist.iterator(); it.hasNext();) {
                LLSDMap group = (LLSDMap) it.next();
                String groupname=((LLSDString)(group.get("GroupName"))).toString();
                LLSDBinary grouppowers=(LLSDBinary) group.get("GroupPowers");
                boolean listinprofile=((LLSDBoolean)group.get("ListInProfile")).get();
                boolean acceptnotices=((LLSDBoolean)group.get("AcceptNotices")).get();
                int contribution=((LLSDInteger)group.get("Contribution")).get();
                LLUUID uuid=((LLSDUUID)group.get("GroupID")).toLLUUID();
                GroupData g;
                if (groups.containsKey(uuid.toLong())) { g=groups.get(uuid.toLong()); } else { g=new GroupData(); }
                g.groupname=groupname;
                g.grouppowers=grouppowers;
                g.listinprofile=listinprofile;
                g.acceptnotices=acceptnotices;
                g.contribution=contribution;
                g.uuid=uuid;
                groups.put(uuid.toLong(),g);
            }
        }
    }
    
    private Map<Long,GroupData> groups=new HashMap<>();
    
    public class GroupData {
        String groupname=null;
        LLSDBinary grouppowers=null;
        boolean listinprofile=true;
        boolean acceptnotices=true;
        int contribution=0;
        LLUUID uuid=null;
    }
    
}
