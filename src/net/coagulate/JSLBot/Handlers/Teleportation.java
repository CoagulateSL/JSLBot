package net.coagulate.JSLBot.Handlers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import net.coagulate.JSLBot.Circuit;
import net.coagulate.JSLBot.CommandEvent;
import net.coagulate.JSLBot.Configuration;
import net.coagulate.JSLBot.Debug;
import net.coagulate.JSLBot.Handler;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.JSLBot.CmdHelp;
import net.coagulate.JSLBot.JSLBot.ParamHelp;
import net.coagulate.JSLBot.LLSD.LLSDArray;
import net.coagulate.JSLBot.LLSD.LLSDBinary;
import net.coagulate.JSLBot.LLSD.LLSDInteger;
import net.coagulate.JSLBot.LLSD.LLSDMap;
import net.coagulate.JSLBot.LLSD.LLSDString;
import net.coagulate.JSLBot.Log;
import net.coagulate.JSLBot.Packets.Messages.ImprovedInstantMessage;
import net.coagulate.JSLBot.Packets.Messages.TeleportLandmarkRequest;
import net.coagulate.JSLBot.Packets.Messages.TeleportLocal;
import net.coagulate.JSLBot.Packets.Messages.TeleportLocationRequest;
import net.coagulate.JSLBot.Packets.Messages.TeleportLureRequest;
import net.coagulate.JSLBot.Packets.Messages.TeleportProgress;
import net.coagulate.JSLBot.Packets.Messages.TeleportStart;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.LLVector3;
import net.coagulate.JSLBot.Packets.Types.U64;
import net.coagulate.JSLBot.Regional;
import net.coagulate.JSLBot.UDPEvent;
import net.coagulate.JSLBot.XMLEvent;

/** Implements the teleportation (sub) protocol
 *
 * @author Iain Price
 */
public class Teleportation extends Handler {

    public Teleportation(JSLBot bot,Configuration c){super(bot,c); config=c;}

    Object signal=new Object();
    boolean teleporting=false;

    @Override
    public void loggedIn() throws Exception {}

    
    public void teleportProgressUDPImmediate(UDPEvent event) {
        TeleportProgress tp=(TeleportProgress) event.body();
        debug(event,"Teleport Progress: "+(tp).binfo.vmessage.toString());
    }
    public void teleportStartUDPImmediate(UDPEvent event) {
        TeleportStart tp=(TeleportStart) event.body();
        info(event,"Teleportation has started (with flags "+tp.binfo.vteleportflags.value+")");
    }
    public void teleportLocalUDPImmediate(UDPEvent event) {
        TeleportLocal tp=(TeleportLocal) event.body();
        info(event,"Teleportation completed locally");
        bot.completeAgentMovement();
        bot.forceAgentUpdate();
        teleporting=false;
        synchronized(signal) { signal.notifyAll(); }
    }

    
    public void teleportFailedXMLImmediate(XMLEvent event) {
        //System.out.println(event.map().toXML());
        String code="";
        String reason="";
        LLSDArray alertinfoarray = (LLSDArray) event.map().get("AlertInfo");
        if (alertinfoarray!=null) {
            LLSDMap inner=(LLSDMap) alertinfoarray.get().get(0);
            code=((LLSDString)(inner.get("Message"))).toString();
        }
        LLSDArray infoarray=(LLSDArray) event.map().get("Info");
        if (infoarray!=null) {
            LLSDMap inner=(LLSDMap)infoarray.get().get(0);
            reason=((LLSDString)(inner.get("Reason"))).toString();
        }
        bot.completeAgentMovement();
        bot.forceAgentUpdate();
        if (code.equalsIgnoreCase("CouldntTPCloser")) {
            teleporting=false;
            note(event,"Teleport couldn't get closer");
        } else {
            warn(event,"Teleport failed ["+code+"] - "+reason);
        }
        synchronized(signal) { signal.notifyAll(); }
    }
    public void teleportFinishXMLImmediate(XMLEvent event) {
        // get the data for the new region
        LLSDMap body=event.map();
        //System.out.println(body.toXML());
        LLSDArray info=(LLSDArray) body.get("Info");
        LLSDMap tpinfo=(LLSDMap) info.get().get(0);
        LLSDBinary simip=(LLSDBinary) tpinfo.get("SimIP");
        LLSDInteger simport=(LLSDInteger) tpinfo.get("SimPort");
        LLSDBinary regionhandle=(LLSDBinary) tpinfo.get("RegionHandle");
        if (Debug.REGIONHANDLES) { debug(event,"TeleportFinish provided regionhandle "+Long.toUnsignedString(regionhandle.toLong())); }
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
            crit(event,"Failed to create teleport finish circuit, we might be losing our connection");
        }

    }

    
    public void improvedInstantMessageUDPDelayed(UDPEvent event) {
        ImprovedInstantMessage m=(ImprovedInstantMessage) event.body();
        int messagetype=m.bmessageblock.vdialog.value;
        String messagetext="["+m.bmessageblock.vfromagentname.toString()+"] "+m.bmessageblock.vmessage.toString();
        // this is a HEAVILY overloaded conduit of information
        // http://wiki.secondlife.com/wiki/ImprovedInstantMessage

        if (messagetype==22) {
            CommandEvent check=new CommandEvent(bot, event.region(), "acceptLures", new HashMap<String,String>(), m.bagentdata.vagentid);
            check.invokerUUID(m.bagentdata.vagentid);
            String reject=bot.brain().auth(check);
            if (reject!=null) { return; } 
            note(event,"Accepting Teleport Lure: "+messagetext);
            TeleportLureRequest req=new TeleportLureRequest();
            req.binfo.vagentid=bot.getUUID();
            req.binfo.vsessionid=bot.getSession();
            req.binfo.vlureid=m.bmessageblock.vid;
            //System.out.println(m.dump());
            teleporting=true;
            bot.send(req,true);
            synchronized(signal) { try { signal.wait(10000); } catch (InterruptedException e) {} }
            if (teleporting==true) {
                crit(event,"Timer expired while teleporting, lost in transit?");
                bot.im(m.bagentdata.vagentid,"Failed to accept teleport lure, lost in transit?");
            } else {
                info(event,"Completed teleport intiated from lure");
                bot.im(m.bagentdata.vagentid,"Accepted teleport lure and completed transit");
            }
        }
    }

    
    public void processXML(Regional region, XMLEvent event, String eventname) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @CmdHelp(description = "Initiate a teleport to a target location")
    public String teleportCommand(Regional r,
            @ParamHelp(description="Name of region to teleport to")
            String region,
            @ParamHelp(description="X Co-ordinate to request")
            String x,
            @ParamHelp(description="Y Co-ordinate to request")
            String y,
            @ParamHelp(description="Z Co-ordinate to request")
            String z) throws IOException {
        TeleportLocationRequest tp=new TeleportLocationRequest();
        tp.bagentdata.vagentid=bot.getUUID();
        tp.bagentdata.vsessionid=bot.getSession();
        tp.binfo.vposition=new LLVector3(x,y,z);
        Map<String,String> lookupparams=new HashMap<>();
        lookupparams.put("name",region);
        String regionhandle=new CommandEvent(bot, bot.getRegional(), "regionLookup", lookupparams, null).execute();
        if (Debug.REGIONHANDLES) { Log.debug(r,"Region lookup for "+region+" gave handle "+new U64(regionhandle)); }
        try { tp.binfo.vregionhandle=new U64(regionhandle);  }
        catch (NumberFormatException e) { return "Failed to resolve region name "+region; }
        bot.send(tp,true);
        //bot.clearUnhandled(); // this just causes us to spew "unhandled packet" alerts from scratch, for debugging at some point
        boolean completed=waitTeleport();
        if (completed) { return "1 - TP Sequence Completed"; } else { return "0 - TP Sequence failed"; }
    }
 
    @CmdHelp(description = "Go home")
    public String homeCommand(Regional r) throws IOException {
        TeleportLandmarkRequest req=new TeleportLandmarkRequest();
        req.binfo.vagentid=bot.getUUID();
        req.binfo.vsessionid=bot.getSession();
        req.binfo.vlandmarkid=new LLUUID();
        bot.send(req,true);
        boolean completed=waitTeleport();
        if (completed) { return "1 - TP Sequence Completed"; } else { return "0 - TP Sequence failed"; }
        
    }
    
    private boolean waitTeleport() throws IOException {
        teleporting=true;
        try { synchronized(signal) { signal.wait(10000); } } catch (InterruptedException e) {}
        if (teleporting==true) { Log.crit(bot,"Timer expired while teleporting, lost in transit?"); } 
        boolean completed=!teleporting;
        teleporting=false;
        bot.setMaxFOV();
        bot.agentUpdate();
        return completed;
    }
    
}
