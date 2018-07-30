package net.coagulate.JSLBot.Handlers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.coagulate.JSLBot.Configuration;
import net.coagulate.JSLBot.Debug;
import net.coagulate.JSLBot.Event;
import net.coagulate.JSLBot.Global;
import net.coagulate.JSLBot.Handler;
import net.coagulate.JSLBot.JSLBot;
import static net.coagulate.JSLBot.Log.*;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Messages.AgentDataUpdate;
import net.coagulate.JSLBot.Packets.Messages.AgentMovementComplete;
import net.coagulate.JSLBot.Packets.Messages.CoarseLocationUpdate;
import net.coagulate.JSLBot.Packets.Messages.CoarseLocationUpdate_bAgentData;
import net.coagulate.JSLBot.Packets.Messages.CoarseLocationUpdate_bLocation;
import net.coagulate.JSLBot.Packets.Messages.TeleportLocal;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.LLVector3;
import net.coagulate.JSLBot.Packets.Types.U64;
import net.coagulate.JSLBot.Regional;

/** Deal with messages about the Agent (and other agents).
 *
 * @author Iain Price <git@predestined.net>
 */
public class Agent extends Handler {
    JSLBot bot;
    public Agent(Configuration c) {super(c);}
    @Override
    public String toString() {
        return "Basic agent operations and information";
    }

    @Override
    public void initialise(JSLBot ai) throws Exception {
        bot=ai;
        ai.addImmediateHandler("AgentDataUpdate", this);
        ai.addImmediateHandler("AgentMovementComplete",this);
        ai.addImmediateHandler("TeleportLocal",this);
        bot.addHandler("CoarseLocationUpdate",this);  // rough agent locations
        ai.addCommand("status", this);
    }

    private String grouptitle="";
    private String groupname="";
    private String firstname="";
    private String lastname="";
    @Override
    public void processImmediate(Event event) throws Exception {
        Message m=event.message();
        if (m!=null) { processImmediate(m,event.getRegion()); }
    }
    private void processImmediate(Message m,Regional regionid) throws Exception {
        if (m instanceof AgentDataUpdate) {
            AgentDataUpdate adu=(AgentDataUpdate) m;
            firstname=adu.bagentdata.vfirstname.toString();
            lastname=adu.bagentdata.vlastname.toString();
            groupname=adu.bagentdata.vgroupname.toString();
            grouptitle=adu.bagentdata.vgrouptitle.toString();
        }
        if (m instanceof AgentMovementComplete) {
            AgentMovementComplete amc=(AgentMovementComplete) m;
            bot.setPos(amc.bdata.vposition);
            bot.setLookAt(amc.bdata.vlookat);
            U64 regionhandle = amc.bdata.vregionhandle;
            if (Debug.REGIONHANDLES) { debug(bot,"AgentMovementComplete discovers region handle "+Long.toUnsignedString(regionhandle.value)); }
        }        
   
        if (m instanceof TeleportLocal) {
            TeleportLocal tp=(TeleportLocal)m;
            bot.setPos(tp.binfo.vposition);
            bot.setLookAt(tp.binfo.vlookat);
        }
    }

    @Override
    public void process(Event event) throws Exception {
        Message m=event.message();
        if (m!=null) { process(m,event.getRegion()); }        
    }
    private void process(Message m,Regional regionid) {
        if (m instanceof CoarseLocationUpdate) {
            coarseLocationUpdate(regionid,(CoarseLocationUpdate) m);
        } 
    }
    @Override
    public String execute(String command, Map<String, String> parameters) throws Exception {
        if (command.equalsIgnoreCase("status")) {
            return "Agent is "+firstname+" "+lastname+" ("+grouptitle+" of "+groupname+")\nPos: "+bot.getPos()+" Looking: "+bot.getLookAt()+"\nRegion: "+bot.circuit().getRegionName();
        }
        return null;
    }

    @Override
    public void loggedIn() throws Exception {
    }

    @Override
    public String help(String command) {
        if (command.equalsIgnoreCase("status")) { return "Reports the status of the agent"; }
        return "Undocumented command";
    }

    private void coarseLocationUpdate(Regional regionid,CoarseLocationUpdate up) { // this is a rough map of all region avatars, as shown in say world map
        if (regionid==null) { note(bot,"Coarse location update for null region, discarding"); return; }
        List<CoarseLocationUpdate_bLocation> locations = up.blocation;
        List<CoarseLocationUpdate_bAgentData> agents = up.bagentdata;
        if (locations.size()!=agents.size()) { log(bot,CRIT,"Equal length co-ord/agent assumption violated"); return; }
        Map<LLUUID,LLVector3> locmap=new HashMap<>();
        for (int i=0;i<locations.size();i++) {
            LLUUID agent=agents.get(i).vagentid;
            LLVector3 location=new LLVector3();
            location.x=locations.get(i).vx.value;
            location.y=locations.get(i).vy.value;
            location.z=locations.get(i).vz.value;
            locmap.put(agent,location);
        }
        regionid.setCoarseAgentLocations(locmap);
    }
    
}
