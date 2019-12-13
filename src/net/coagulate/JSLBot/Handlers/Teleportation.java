package net.coagulate.JSLBot.Handlers;

import net.coagulate.JSLBot.*;
import net.coagulate.JSLBot.JSLBot.CmdHelp;
import net.coagulate.JSLBot.JSLBot.ParamHelp;
import net.coagulate.JSLBot.LLSD.*;
import net.coagulate.JSLBot.Packets.Messages.*;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.LLVector3;
import net.coagulate.JSLBot.Packets.Types.U64;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/** Handles teleportation, requests, status, success, failure, going home, tp lures, etc.
 *
 * @author Iain Price
 */
public class Teleportation extends Handler {

    public Teleportation(@Nonnull JSLBot bot, Configuration c){super(bot,c); config=c;}

    final Object signal=new Object();
    boolean teleporting=false;

    // nothing more than a status message
    public void teleportProgressUDPImmediate(@Nonnull UDPEvent event) {
        TeleportProgress tp=(TeleportProgress) event.body();
        log.fine("Teleport Progress: "+(tp).binfo.vmessage.toString());
    }
    // also just a status message
    public void teleportStartUDPImmediate(@Nonnull UDPEvent event) {
        TeleportStart tp=(TeleportStart) event.body();
        log.fine("Teleportation has started (with flags "+tp.binfo.vteleportflags.value+")");
    }
    // completion message, without any of the complexities of changing region
    public void teleportLocalUDPImmediate(@Nonnull UDPEvent event) {
        TeleportLocal tp=(TeleportLocal) event.body();
        log.info("Teleportation completed locally");
        bot.completeAgentMovement();
        bot.forceAgentUpdate();
        teleporting=false;
        synchronized(signal) { signal.notifyAll(); }
    }

    // failure
    public void teleportFailedXMLImmediate(@Nonnull XMLEvent event) {
        //System.out.println(event.map().toXML());
        String code="";
        String reason="";
        LLSDArray alertinfoarray = (LLSDArray) event.map().get("AlertInfo");
        if (alertinfoarray!=null) {
            LLSDMap inner=(LLSDMap) alertinfoarray.get().get(0);
            code= inner.get("Message").toString();
        }
        LLSDArray infoarray=(LLSDArray) event.map().get("Info");
        if (infoarray!=null) {
            LLSDMap inner=(LLSDMap)infoarray.get().get(0);
            reason= inner.get("Reason").toString();
        }
        bot.completeAgentMovement();
        bot.forceAgentUpdate();
        if ("CouldntTPCloser".equalsIgnoreCase(code)) {
            teleporting=false;
            log.info("Teleport couldn't get closer");
        } else {
            log.warning("Teleport failed ["+code+"] - "+reason);
        }
        synchronized(signal) { signal.notifyAll(); }
    }
    // success, transfer to target circuit/caps
    public void teleportFinishXMLImmediate(@Nonnull XMLEvent event) {
        // get the data for the new region
        LLSDMap body=event.map();
        //System.out.println(body.toXML());
        LLSDArray info=(LLSDArray) body.get("Info");
        LLSDMap tpinfo=(LLSDMap) info.get().get(0);
        LLSDBinary simip=(LLSDBinary) tpinfo.get("SimIP");
        LLSDInteger simport=(LLSDInteger) tpinfo.get("SimPort");
        LLSDBinary regionhandle=(LLSDBinary) tpinfo.get("RegionHandle");
        if (Debug.REGIONHANDLES) { log.fine("TeleportFinish provided regionhandle "+Long.toUnsignedString(regionhandle.toLong())); }
        String targetaddress=simip.toIP();
        // create the circuit and transfer to it
        //System.out.println(event.body().toXML());
        LLSDString caps=(LLSDString) tpinfo.get("SeedCapability");
        try {
            Circuit circuit=bot.createCircuit(targetaddress,simport.get(),regionhandle.toLong(),caps.toString());
            bot.setPrimaryCircuit(circuit);
            bot.completeAgentMovement();
            bot.forceAgentUpdate();
            // fire up the event queue
            // set flag, notify the waiting thread
            teleporting=false;
            synchronized(signal) { signal.notifyAll(); }
        } catch (IOException e) {
            log.severe("Failed to create teleport finish circuit, we might be losing our connection");
        }

    }
    // of TP lures
    public void improvedInstantMessageUDPDelayed(@Nonnull UDPEvent event) {
        ImprovedInstantMessage m=(ImprovedInstantMessage) event.body();
        int messagetype=m.bmessageblock.vdialog.value;
        String messagetext="["+m.bmessageblock.vfromagentname.toString()+"] "+m.bmessageblock.vmessage.toString();
        // this is a HEAVILY overloaded conduit of information
        // http://wiki.secondlife.com/wiki/ImprovedInstantMessage

        if (messagetype==22) {
            CommandEvent check=new CommandEvent(bot, event.region(), "acceptLures", new HashMap<>(), m.bagentdata.vagentid);
            check.invokerUUID(m.bagentdata.vagentid);
            String reject=bot.brain().auth(check);
            if (reject!=null) { return; } 
            log.info("Accepting Teleport Lure: "+messagetext);
            TeleportLureRequest req=new TeleportLureRequest();
            req.binfo.vagentid=bot.getUUID();
            req.binfo.vsessionid=bot.getSession();
            req.binfo.vlureid=m.bmessageblock.vid;
            //System.out.println(m.dump());
            teleporting=true;
            bot.send(req,true);
            synchronized(signal) { try { signal.wait(10000); } catch (InterruptedException e) {} }
            if (teleporting) {
                log.severe("Timer expired while teleporting, lost in transit?");
                bot.im(m.bagentdata.vagentid,"Failed to accept teleport lure, lost in transit?");
            } else {
                log.info("Completed teleport intiated from lure");
                bot.im(m.bagentdata.vagentid,"Accepted teleport lure and completed transit");
            }
        }
    }
    // request TP
    @Nonnull
    @CmdHelp(description = "Initiate a teleport to a target location")
    public String teleportCommand(@Nonnull CommandEvent command,
                                  @ParamHelp(description="Name of region to teleport to")
            String region,
                                  @ParamHelp(description="X Co-ordinate to request")
            String x,
                                  @ParamHelp(description="Y Co-ordinate to request")
            String y,
                                  @ParamHelp(description="Z Co-ordinate to request")
            String z) {
        Regional r=command.region();
        TeleportLocationRequest tp=new TeleportLocationRequest();
        tp.bagentdata.vagentid=bot.getUUID();
        tp.bagentdata.vsessionid=bot.getSession();
        tp.binfo.vposition=new LLVector3(x,y,z);
        Map<String,String> lookupparams=new HashMap<>();
        lookupparams.put("name",region);
        String regionhandle=new CommandEvent(bot, bot.getRegional(), "regionLookup", lookupparams, null).execute();
        if (Debug.REGIONHANDLES) { log.fine("Region lookup for "+region+" gave handle "+new U64(regionhandle)); }
        try { tp.binfo.vregionhandle=new U64(regionhandle);  }
        catch (NumberFormatException e) { return "Failed to resolve region name "+region; }
        bot.send(tp,true);
        //bot.clearUnhandled(); // this just causes us to spew "unhandled packet" alerts from scratch, for debugging at some point
        boolean completed=waitTeleport();
        log.info("Teleport "+(completed?"completed":"FAILED")+" to "+region+" "+x+","+y+","+z);
        if (completed) { return "1 - TP Sequence Completed"; } else { return "0 - TP Sequence failed"; }
    }

    @Nonnull
    @CmdHelp(description = "Go home")
    public String homeCommand(CommandEvent command) {
        TeleportLandmarkRequest req=new TeleportLandmarkRequest();
        req.binfo.vagentid=bot.getUUID();
        req.binfo.vsessionid=bot.getSession();
        req.binfo.vlandmarkid=new LLUUID();
        bot.send(req,true);
        boolean completed=waitTeleport();
        log.info("Teleport home "+(completed?"completed":"FAILED"));
        if (completed) { return "1 - TP Sequence Completed"; } else { return "0 - TP Sequence failed"; }
        
    }
    
    private boolean waitTeleport() {
        teleporting=true;
        boolean expired=false;
        try { synchronized(signal) { signal.wait(10000); expired=true; } } catch (InterruptedException e) {}
        if (expired) { log.severe("Timer expired while teleporting, lost in transit?"); }
        boolean completed=!teleporting;
        teleporting=false;
        bot.setMaxFOV();
        bot.agentUpdate();
        return completed;
    }
    
    @Nonnull
    @CmdHelp(description = "Sends you a teleport lure")
    public String lureMeCommand(@Nonnull CommandEvent command) {
        LLUUID targetuuid=command.invokerUUID();
        if (targetuuid==null) { return "Failed to get target"; }
        StartLure req=new StartLure(bot);
        req.binfo.vmessage=new Variable1("Luring you, as requested");
        req.btargetdata=new ArrayList<>();
        StartLure_bTargetData target=new StartLure_bTargetData();
        target.vtargetid=targetuuid;
        req.btargetdata.add(target);
        bot.send(req,true);
        return "0 - TP Lure Request Sent";
    }
    
    @Nonnull
    @CmdHelp(description = "Sends a teleport lure")
    public String lureCommand(@Nonnull CommandEvent command,
                              @ParamHelp(description="UUID to lure")
            String uuid) {
        LLUUID targetuuid=new LLUUID(uuid);
        if (targetuuid==null) { return "Failed to get target"; }
        StartLure req=new StartLure(bot);
        req.binfo.vmessage=new Variable1("Sending lure, as requested by "+command.invokerUsername()+" ["+command.invokerUUID().toUUIDString()+"]");
        req.btargetdata=new ArrayList<>();
        StartLure_bTargetData target=new StartLure_bTargetData();
        target.vtargetid=targetuuid;
        req.btargetdata.add(target);
        bot.send(req,true);
        return "0 - TP Lure Request Sent";
    }    
    
}
