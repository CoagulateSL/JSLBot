package net.coagulate.JSLBot.Handlers;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.coagulate.JSLBot.Configuration;
import net.coagulate.JSLBot.Debug;
import net.coagulate.JSLBot.Handler;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.JSLBot.CmdHelp;
import net.coagulate.JSLBot.JSLBot.ParamHelp;
import net.coagulate.JSLBot.Log;
import static net.coagulate.JSLBot.Log.*;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Messages.AgentDataUpdate;
import net.coagulate.JSLBot.Packets.Messages.AgentMovementComplete;
import net.coagulate.JSLBot.Packets.Messages.CoarseLocationUpdate;
import net.coagulate.JSLBot.Packets.Messages.CoarseLocationUpdate_bAgentData;
import net.coagulate.JSLBot.Packets.Messages.CoarseLocationUpdate_bLocation;
import net.coagulate.JSLBot.Packets.Messages.MoneyBalanceReply;
import net.coagulate.JSLBot.Packets.Messages.MoneyBalanceRequest;
import net.coagulate.JSLBot.Packets.Messages.OfflineNotification;
import net.coagulate.JSLBot.Packets.Messages.OfflineNotification_bAgentBlock;
import net.coagulate.JSLBot.Packets.Messages.OnlineNotification;
import net.coagulate.JSLBot.Packets.Messages.OnlineNotification_bAgentBlock;
import net.coagulate.JSLBot.Packets.Messages.TeleportLocal;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.LLVector3;
import net.coagulate.JSLBot.Packets.Types.U64;
import net.coagulate.JSLBot.Regional;
import net.coagulate.JSLBot.UDPEvent;
import net.coagulate.JSLBot.XMLEvent;

/** Deal with messages about the Agent (and other agents).
 *
 * @author Iain Price
 */
public class Agent extends Handler {
    public Agent(JSLBot bot,Configuration c) {super(bot,c);}
    @Override
    public String toString() {
        return "Basic agent operations and information";
    }

    @Override
    public void initialise() throws Exception {
        bot.addImmediateUDP("AgentDataUpdate", this);
        bot.addImmediateUDP("AgentMovementComplete",this);
        bot.addImmediateUDP("TeleportLocal",this);
        bot.addImmediateUDP("MoneyBalanceReply",this);
        bot.addImmediateUDP("OnlineNotification", this); // we actually notify on this oO
        bot.addImmediateUDP("OfflineNotification", this); // we actually notify on this oO        
        bot.addUDP("CoarseLocationUpdate",this);  // rough agent locations
        bot.addCommand("status", this);
        bot.addCommand("maxfov", this);
        bot.addCommand("minfov", this);
        bot.addCommand("update", this);
        bot.addCommand("draw", this);
    }

    private String grouptitle="";
    private String groupname="";
    private String firstname="";
    private String lastname="";
    private void processImmediate(Message m,Regional regionid) throws Exception {

    }

    @Override
    public void loggedIn() throws Exception {
        // get financials
        MoneyBalanceRequest req=new MoneyBalanceRequest();
        req.bagentdata.vagentid=bot.getUUID();
        req.bagentdata.vsessionid=bot.getSession();
        req.bmoneydata.vtransactionid=new LLUUID();
        bot.send(req,true);
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

    private final Set<LLUUID> online=new HashSet<>();
    private final Set<LLUUID> offline=new HashSet<>();
    private int balance=0;
    @Override
    public void processImmediateUDP(Regional region, UDPEvent event, String eventname) throws Exception {
        Message m=event.body();
        if (eventname.equals("OnlineNotification")) {
            List<OnlineNotification_bAgentBlock> agents=((OnlineNotification)event.body()).bagentblock;
            for (OnlineNotification_bAgentBlock block:agents) {
                LLUUID uuid=block.vagentid;
                synchronized(online) {
                    if (!online.contains(uuid)) {
                        Log.log(bot, INFO, "Friend ONLINE: "+bot.getDisplayName(uuid)+" ("+bot.getUserName(uuid)+") ["+uuid.toUUIDString()+"]");
                        online.add(uuid);
                    }
                }
                synchronized(offline) { offline.remove(uuid); }
            }
        }
        if (eventname.equals("OfflineNotification")) {
            List<OfflineNotification_bAgentBlock> agents=((OfflineNotification)event.body()).bagentblock;
            for (OfflineNotification_bAgentBlock block:agents) {
                LLUUID uuid=block.vagentid;
                synchronized (offline) {
                    if (!offline.contains(uuid)) { 
                        Log.log(bot, INFO, "Friend offline: "+bot.getDisplayName(uuid)+" ("+bot.getUserName(uuid)+") ["+uuid.toUUIDString()+"]");
                        offline.add(uuid);
                    }
                }
                synchronized(online) { online.remove(uuid); }
            }
        }        
        if (eventname.equals("AgentDataUpdate")) {
            AgentDataUpdate adu=(AgentDataUpdate) m;
            firstname=adu.bagentdata.vfirstname.toString();
            lastname=adu.bagentdata.vlastname.toString();
            groupname=adu.bagentdata.vgroupname.toString();
            grouptitle=adu.bagentdata.vgrouptitle.toString();
        }
        if (eventname.equals("AgentMovementComplete")) {
            AgentMovementComplete amc=(AgentMovementComplete) m;
            bot.setPos(amc.bdata.vposition);
            bot.setLookAt(amc.bdata.vlookat);
            U64 regionhandle = amc.bdata.vregionhandle;
            if (Debug.REGIONHANDLES) { debug(bot,"AgentMovementComplete discovers region handle "+Long.toUnsignedString(regionhandle.value)); }
        }        
   
        if (eventname.equals("TeleportLocal")) {
            TeleportLocal tp=(TeleportLocal)m;
            bot.setPos(tp.binfo.vposition);
            bot.setLookAt(tp.binfo.vlookat);
        }
        if (eventname.equals("MoneyBalanceReply")) {
            MoneyBalanceReply money = (MoneyBalanceReply)m;
            balance=money.bmoneydata.vmoneybalance.value;
            int sqmcredit=money.bmoneydata.vsquaremeterscredit.value;
            int sqmspent=money.bmoneydata.vsquaremeterscommitted.value;
            String description=money.bmoneydata.vdescription.toString();
            note(bot,"Balance: "+balance+"L$, Land: "+sqmspent+"m2/"+sqmcredit+"m2 "+description);
        }
    }

    @Override
    public void processImmediateXML(Regional region, XMLEvent event, String eventname) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void processUDP(Regional region, UDPEvent event, String eventname) throws Exception {
        if (eventname.equals("CoarseLocationUpdate")) {
            coarseLocationUpdate(region,(CoarseLocationUpdate) event.body());
        } 
    }

    @Override
    public void processXML(Regional region, XMLEvent event, String eventname) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @CmdHelp(description = "Returns some basic information about the logged in agent")
    public String statusCommand(Regional region) {
        return "Agent is "+firstname+" "+lastname+" ("+grouptitle+" of "+groupname+")\nPos: "+bot.getPos()+" Looking: "+bot.getLookAt()+"\nRegion: "+bot.getRegionName();
    }

    @CmdHelp(description="Sets the FOV (field of view) to TWO_PI")
    public String maxfovCommand(Regional region) throws IOException { bot.setMaxFOV(); return "Set"; }
    @CmdHelp(description="Sets the FOV (field of view) to Zero")
    public String minfovCommand(Regional region) throws IOException { bot.setMinFOV(); return "Set"; }    
    @CmdHelp(description="Send agent update")
    public String updateCommand(Regional region) throws IOException { bot.agentUpdate(); return "Sent"; }
    @CmdHelp(description = "Set agent's draw distance")
    public String drawCommand(Regional region,
            @ParamHelp(description="Meters draw distance")
            String distance) throws IOException {
        bot.drawDistance(Float.parseFloat(distance));
        return "Draw Distance Set";
    }
}

