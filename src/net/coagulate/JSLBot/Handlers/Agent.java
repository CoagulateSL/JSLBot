package net.coagulate.JSLBot.Handlers;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.coagulate.JSLBot.CommandEvent;
import net.coagulate.JSLBot.Configuration;
import net.coagulate.JSLBot.Debug;
import net.coagulate.JSLBot.Handler;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.JSLBot.CmdHelp;
import net.coagulate.JSLBot.JSLBot.ParamHelp;
import net.coagulate.JSLBot.Packets.Messages.AgentDataUpdate;
import net.coagulate.JSLBot.Packets.Messages.AgentMovementComplete;
import net.coagulate.JSLBot.Packets.Messages.AlertMessage;
import net.coagulate.JSLBot.Packets.Messages.CoarseLocationUpdate;
import net.coagulate.JSLBot.Packets.Messages.CoarseLocationUpdate_bAgentData;
import net.coagulate.JSLBot.Packets.Messages.CoarseLocationUpdate_bLocation;
import net.coagulate.JSLBot.Packets.Messages.MoneyBalanceReply;
import net.coagulate.JSLBot.Packets.Messages.MoneyBalanceRequest;
import net.coagulate.JSLBot.Packets.Messages.OfflineNotification;
import net.coagulate.JSLBot.Packets.Messages.OfflineNotification_bAgentBlock;
import net.coagulate.JSLBot.Packets.Messages.OnlineNotification;
import net.coagulate.JSLBot.Packets.Messages.OnlineNotification_bAgentBlock;
import net.coagulate.JSLBot.Packets.Messages.SetStartLocationRequest;
import net.coagulate.JSLBot.Packets.Messages.TeleportLocal;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.LLVector3;
import net.coagulate.JSLBot.Packets.Types.U64;
import net.coagulate.JSLBot.Packets.Types.Variable1;
import net.coagulate.JSLBot.UDPEvent;

/** Deal with messages about the Agent (and other agents).
 *
 * @author Iain Price
 */
public class Agent extends Handler {
    public Agent(JSLBot bot,Configuration c) {super(bot,c);}
    
    private String grouptitle="";
    private String groupname="";
    private String firstname="";
    private String lastname="";
    
    @Override
    public void loggedIn() throws Exception {
        // get financials
        MoneyBalanceRequest req=new MoneyBalanceRequest();
        req.bagentdata.vagentid=bot.getUUID();
        req.bagentdata.vsessionid=bot.getSession();
        req.bmoneydata.vtransactionid=new LLUUID();
        bot.send(req,true);
    }

    private final Set<LLUUID> online=new HashSet<>();
    private final Set<LLUUID> offline=new HashSet<>();
    private int balance=0;
   
    public void agentDataUpdateUDPImmediate(UDPEvent event) {
        AgentDataUpdate adu=(AgentDataUpdate) event.body();
        firstname=adu.bagentdata.vfirstname.toString();
        lastname=adu.bagentdata.vlastname.toString();
        groupname=adu.bagentdata.vgroupname.toString();
        grouptitle=adu.bagentdata.vgrouptitle.toString();

    } 

    public void onlineNotificationUDPImmediate(UDPEvent event) {
        List<OnlineNotification_bAgentBlock> agents=((OnlineNotification)event.body()).bagentblock;
        for (OnlineNotification_bAgentBlock block:agents) {
            LLUUID uuid=block.vagentid;
            synchronized(online) {
                if (!online.contains(uuid)) {
                    info(event,"Friend ONLINE: "+bot.getDisplayName(uuid)+" ("+bot.getUserName(uuid)+") ["+uuid.toUUIDString()+"]");
                    online.add(uuid);
                }
            }
            synchronized(offline) { offline.remove(uuid); }
        }
    }
    public void offlineNotificationUDPImmediate(UDPEvent event) {
        List<OfflineNotification_bAgentBlock> agents=((OfflineNotification)event.body()).bagentblock;
        for (OfflineNotification_bAgentBlock block:agents) {
            LLUUID uuid=block.vagentid;
            synchronized (offline) {
                if (!offline.contains(uuid)) { 
                    info(event,"Friend offline: "+bot.getDisplayName(uuid)+" ("+bot.getUserName(uuid)+") ["+uuid.toUUIDString()+"]");
                    offline.add(uuid);
                }
            }
            synchronized(online) { online.remove(uuid); }
        }
    }        
    public void agentMovementCompleteUDPImmediate(UDPEvent event) {
        AgentMovementComplete amc=(AgentMovementComplete) event.body();
        bot.setPos(amc.bdata.vposition);
        bot.setLookAt(amc.bdata.vlookat);
        U64 regionhandle = amc.bdata.vregionhandle;
        if (Debug.REGIONHANDLES) { debug(event,"AgentMovementComplete discovers region handle "+Long.toUnsignedString(regionhandle.value)); }
    }
    public void teleportLocalUDPImmediate(UDPEvent event) {
        TeleportLocal tp=(TeleportLocal)event.body();
        bot.setPos(tp.binfo.vposition);
        bot.setLookAt(tp.binfo.vlookat);
    }
    public void moneyBalanceReplyUDPImmediate(UDPEvent event) {
        MoneyBalanceReply money = (MoneyBalanceReply)event.body();
        balance=money.bmoneydata.vmoneybalance.value;
        int sqmcredit=money.bmoneydata.vsquaremeterscredit.value;
        int sqmspent=money.bmoneydata.vsquaremeterscommitted.value;
        String description=money.bmoneydata.vdescription.toString();
        note(event,"Balance: "+balance+"L$, Land: "+sqmspent+"m2/"+sqmcredit+"m2 "+description);
    }

    @CmdHelp(description = "Returns some basic information about the logged in agent")
    public String statusCommand(CommandEvent command) {
        return "Agent is "+firstname+" "+lastname+" ("+grouptitle+" of "+groupname+")\nPos: "+bot.getPos()+" Looking: "+bot.getLookAt()+"\nRegion: "+bot.getRegionName();
    }
    @CmdHelp(description="Sets the FOV (field of view) to TWO_PI")
    public String fovMaxCommand(CommandEvent command) throws IOException { bot.setMaxFOV(); return "Set"; }
    @CmdHelp(description="Sets the FOV (field of view) to Zero")
    public String fovMinCommand(CommandEvent command) throws IOException { bot.setMinFOV(); return "Set"; }    
    @CmdHelp(description="Send agent update")
    public String updateCommand(CommandEvent command) throws IOException { bot.agentUpdate(); return "Sent"; }
    @CmdHelp(description = "Set agent's draw distance")
    public String drawdistsanceCommand(CommandEvent command,
            @ParamHelp(description="Meters draw distance")
            String set) throws IOException {
        bot.drawDistance(Float.parseFloat(set));
        return "Draw Distance Set";
    }
    
    
    public void coarseLocationUpdateUDPDelayed(UDPEvent event) {
        CoarseLocationUpdate up=(CoarseLocationUpdate) event.body();
        if (event.region()==null) { note(event,"Coarse location update for null region, discarding"); return; }
        List<CoarseLocationUpdate_bLocation> locations = up.blocation;
        List<CoarseLocationUpdate_bAgentData> agents = up.bagentdata;
        if (locations.size()!=agents.size()) { crit(event,"Equal length co-ord/agent assumption violated"); return; }
        Map<LLUUID,LLVector3> locmap=new HashMap<>();
        for (int i=0;i<locations.size();i++) {
            LLUUID agent=agents.get(i).vagentid;
            LLVector3 location=new LLVector3();
            location.x=locations.get(i).vx.value;
            location.y=locations.get(i).vy.value;
            location.z=locations.get(i).vz.value;
            locmap.put(agent,location);
        }
        event.region().setCoarseAgentLocations(locmap);
    }

    private LLUUID reporthometo=null;
    @CmdHelp(description = "Set the agent's start location")
    public String setHomeCommand(CommandEvent event) {
        reporthometo=event.respondTo();
        SetStartLocationRequest req=new SetStartLocationRequest(bot);
        req.bstartlocationdata.vsimname=new Variable1(bot.getRegionName());
        req.bstartlocationdata.vlocationid.value=1;
        req.bstartlocationdata.vlocationpos=bot.getPos();
        req.bstartlocationdata.vlocationlookat=bot.getLookAt();
        bot.send(req,true);
        return "0 - Set Home request sent";
    }

    public void alertMessageUDPDelayed(UDPEvent event) {
        AlertMessage a=(AlertMessage) event.body();
        if (a.balertinfo.size()>0) {
            // this is a sweeping assumption, however, without knowing the full list of possibilities, i.e. the server code, this seems reasonable :|
            if (a.balertinfo.get(0).vmessage.toString().toLowerCase().contains("home")) {
                if (reporthometo!=null) {
                    bot.im(reporthometo,a.balertdata.vmessage.toString());
                }
            }
        }
    }
}

