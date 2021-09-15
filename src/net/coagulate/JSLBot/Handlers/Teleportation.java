package net.coagulate.JSLBot.Handlers;

import net.coagulate.JSLBot.*;
import net.coagulate.JSLBot.JSLBot.CmdHelp;
import net.coagulate.JSLBot.JSLBot.Param;
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

/**
 * Handles teleportation, requests, status, success, failure, going home, tp lures, etc.
 *
 * @author Iain Price
 */
public class Teleportation extends Handler {

	final Object signal=new Object();
	boolean teleporting;

	public Teleportation(@Nonnull final JSLBot bot,
	                     final Configuration c) {
		super(bot,c);
		config=c;
	}

	// ---------- INSTANCE ----------
	// nothing more than a status message
	public void teleportProgressUDPImmediate(@Nonnull final UDPEvent event) {
		final TeleportProgress tp=(TeleportProgress) event.body();
		log.fine("Teleport Progress: "+(tp).binfo.vmessage);
	}

	// also just a status message
	public void teleportStartUDPImmediate(@Nonnull final UDPEvent event) {
		final TeleportStart tp=(TeleportStart) event.body();
		log.fine("Teleportation has started (with flags "+tp.binfo.vteleportflags.value+")");
	}

	// completion message, without any of the complexities of changing region
	public void teleportLocalUDPImmediate(@Nonnull final UDPEvent event) {
		final TeleportLocal tp=(TeleportLocal) event.body();
		log.info("Teleportation completed locally");
		bot.completeAgentMovement();
		bot.forceAgentUpdate();
		teleporting=false;
		synchronized (signal) { signal.notifyAll(); }
	}

	// failure
	public void teleportFailedXMLImmediate(@Nonnull final XMLEvent event) {
		//System.out.println(event.map().toXML());
		String code="";
		String reason="";
		final LLSDArray alertinfoarray=(LLSDArray) event.map().get("AlertInfo");
		if (alertinfoarray!=null) {
			final LLSDMap inner=(LLSDMap) alertinfoarray.get().get(0);
			code=inner.get("Message").toString();
		}
		final LLSDArray infoarray=(LLSDArray) event.map().get("Info");
		if (infoarray!=null) {
			final LLSDMap inner=(LLSDMap) infoarray.get().get(0);
			reason=inner.get("Reason").toString();
		}
		bot.completeAgentMovement();
		bot.forceAgentUpdate();
		if ("CouldntTPCloser".equalsIgnoreCase(code)) {
			teleporting=false;
			log.info("Teleport couldn't get closer");
		}
		else {
			log.warning("Teleport failed ["+code+"] - "+reason);
		}
		synchronized (signal) { signal.notifyAll(); }
	}

	// success, transfer to target circuit/caps
	public void teleportFinishXMLImmediate(@Nonnull final XMLEvent event) {
		// get the data for the new region
		final LLSDMap body=event.map();
		//System.out.println(body.toXML());
		final LLSDArray info=(LLSDArray) body.get("Info");
		final LLSDMap tpinfo=(LLSDMap) info.get().get(0);
		final LLSDBinary simip=(LLSDBinary) tpinfo.get("SimIP");
		final LLSDInteger simport=(LLSDInteger) tpinfo.get("SimPort");
		final LLSDBinary regionhandle=(LLSDBinary) tpinfo.get("RegionHandle");
		if (Debug.REGIONHANDLES) {
			log.fine("TeleportFinish provided regionhandle "+Long.toUnsignedString(regionhandle.toLong()));
		}
		final String targetaddress=simip.toIP();
		// create the circuit and transfer to it
		//System.out.println(event.body().toXML());
		final LLSDString caps=(LLSDString) tpinfo.get("SeedCapability");
		try {
			final Circuit circuit=bot.createCircuit(targetaddress,simport.get(),regionhandle.toLong(),caps.toString());
			bot.setPrimaryCircuit(circuit);
			bot.completeAgentMovement();
			bot.forceAgentUpdate();
			// fire up the event queue
			// set flag, notify the waiting thread
			teleporting=false;
			synchronized (signal) { signal.notifyAll(); }
		}
		catch (@Nonnull final IOException e) {
			log.severe("Failed to create teleport finish circuit, we might be losing our connection");
		}

	}

	// of TP lures
	public void improvedInstantMessageUDPDelayed(@Nonnull final UDPEvent event) {
		final ImprovedInstantMessage m=(ImprovedInstantMessage) event.body();
		final int messagetype=m.bmessageblock.vdialog.value;
		final String messagetext="["+m.bmessageblock.vfromagentname+"] "+m.bmessageblock.vmessage;
		// this is a HEAVILY overloaded conduit of information
		// http://wiki.secondlife.com/wiki/ImprovedInstantMessage

		if (messagetype==22) {
			final CommandEvent check=new CommandEvent(bot,event.region(),"acceptLures",new HashMap<>(),m.bagentdata.vagentid);
			check.invokerUUID(m.bagentdata.vagentid);
			final String reject=bot.brain().auth(check);
			if (reject!=null) { return; }
			log.info("Accepting Teleport Lure: "+messagetext);
			final TeleportLureRequest req=new TeleportLureRequest();
			req.binfo.vagentid=bot.getUUID();
			req.binfo.vsessionid=bot.getSession();
			req.binfo.vlureid=m.bmessageblock.vid;
			//System.out.println(m.dump());
			teleporting=true;
			bot.send(req,true);
			synchronized (signal) {
				try { signal.wait(10000); } catch (@Nonnull final InterruptedException e) {}
			}
			if (teleporting) {
				log.severe("Timer expired while teleporting, lost in transit?");
				bot.im(m.bagentdata.vagentid,"Failed to accept teleport lure, lost in transit?");
			}
			else {
				log.info("Completed teleport intiated from lure");
				bot.im(m.bagentdata.vagentid,"Accepted teleport lure and completed transit");
			}
		}
	}

	// request TP
	@Nonnull
	@CmdHelp(description="Initiate a teleport to a target location")
	public String teleportCommand(@Nonnull final CommandEvent command,
								  @Param(name="region",description="Name of region to teleport to") final String region,
								  @Nonnull @Param(name="x", description="X Co-ordinate to request") final String x,
								  @Nonnull @Param(name="y", description="Y Co-ordinate to request") final String y,
								  @Nonnull @Param(name="z", description="Z Co-ordinate to request") final String z) {
		final Regional r=command.region();
		final TeleportLocationRequest tp=new TeleportLocationRequest();
		tp.bagentdata.vagentid=bot.getUUID();
		tp.bagentdata.vsessionid=bot.getSession();
		tp.binfo.vposition=new LLVector3(x,y,z);
		final Map<String,String> lookupparams=new HashMap<>();
		lookupparams.put("name",region);
		final String regionhandle=new CommandEvent(bot,bot.getRegional(),"regionLookup",lookupparams,null).execute();
		if (Debug.REGIONHANDLES) { log.fine("Region lookup for "+region+" gave handle "+new U64(regionhandle)); }
		try { tp.binfo.vregionhandle=new U64(regionhandle); }
		catch (@Nonnull final NumberFormatException e) {
			return "Failed to resolve region name "+region;
		}
		bot.send(tp,true);
		//bot.clearUnhandled(); // this just causes us to spew "unhandled packet" alerts from scratch, for debugging at some point
		final boolean completed=waitTeleport();
		log.info("Teleport "+(completed?"completed":"FAILED")+" to "+region+" "+x+","+y+","+z);
		if (completed) { return "1 - TP Sequence Completed"; }
		else { return "0 - TP Sequence failed"; }
	}

	@Nonnull
	@CmdHelp(description="Go home")
	public String homeCommand(final CommandEvent command) {
		final TeleportLandmarkRequest req=new TeleportLandmarkRequest();
		req.binfo.vagentid=bot.getUUID();
		req.binfo.vsessionid=bot.getSession();
		req.binfo.vlandmarkid=new LLUUID();
		bot.send(req,true);
		final boolean completed=waitTeleport();
		log.info("Teleport home "+(completed?"completed":"FAILED"));
		if (completed) {
			if (!bot.getHomeSeat().isBlank()) {
				if (!bot.getHomeSeat().isBlank()) {
					Map<String, String> args = new HashMap<>();
					args.put("uuid", bot.getHomeSeat());
					CommandEvent sit = new CommandEvent(bot, null, "siton", args, new LLUUID(config.get("CnC.authorisation.owneruuid")));
					bot.brain().queue(sit);
					return "1 - Home sequence with sit complete";
				}
			}
			return "1 - Home sequence completed";
		}
		else { return "0 - Home Sequence failed"; }

	}

	@Nonnull
	@CmdHelp(description="Sends you a teleport lure")
	public String lureMeCommand(@Nonnull final CommandEvent command) {
		final LLUUID targetuuid=command.invokerUUID();
		if (targetuuid==null) { return "Failed to get target"; }
		final StartLure req=new StartLure(bot);
		req.binfo.vmessage=new Variable1("Luring you, as requested");
		req.btargetdata=new ArrayList<>();
		final StartLure_bTargetData target=new StartLure_bTargetData();
		target.vtargetid=targetuuid;
		req.btargetdata.add(target);
		bot.send(req,true);
		return "0 - TP Lure Request Sent";
	}

	@Nonnull
	@CmdHelp(description="Sends a teleport lure")
	public String lureCommand(@Nonnull final CommandEvent command,
	                          @Nonnull @Param(name="uuid",description="UUID to lure") final String uuid) {
		final LLUUID targetuuid=new LLUUID(uuid);
		final StartLure req=new StartLure(bot);
		String invokeruuid="???";
		final LLUUID invuuid=command.invokerUUID();
		if (invuuid!=null) { invokeruuid=invuuid.toUUIDString(); }
		req.binfo.vmessage=new Variable1("Sending lure, as requested by "+command.invokerUsername()+" ["+invokeruuid+"]");
		req.btargetdata=new ArrayList<>();
		final StartLure_bTargetData target=new StartLure_bTargetData();
		target.vtargetid=targetuuid;
		req.btargetdata.add(target);
		bot.send(req,true);
		return "0 - TP Lure Request Sent";
	}

	// ----- Internal Instance -----
	private boolean waitTeleport() {
		teleporting=true;
		boolean expired=false;
		try {
			synchronized (signal) {
				signal.wait(10000);
				expired=true;
			}
		}
		catch (@Nonnull final InterruptedException e) {}
		if (expired) { log.severe("Timer expired while teleporting, lost in transit?"); }
		final boolean completed=!teleporting;
		teleporting=false;
		bot.setMaxFOV();
		bot.agentUpdate();
		return completed;
	}

}
