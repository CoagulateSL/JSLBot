package net.coagulate.JSLBot.Handlers;

import net.coagulate.JSLBot.*;
import net.coagulate.JSLBot.JSLBot.CmdHelp;
import net.coagulate.JSLBot.JSLBot.ParamHelp;
import net.coagulate.JSLBot.Packets.Messages.*;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.LLVector3;
import net.coagulate.JSLBot.Packets.Types.U64;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.logging.Level;

/**
 * Deal with messages about the Agent (and other agents).
 *
 * @author Iain Price
 */
public class Agent extends Handler {
	private final Set<LLUUID> online=new HashSet<>();
	private final Set<LLUUID> offline=new HashSet<>();
	private String grouptitle="";
	private String groupname="";
	private String firstname="";
	private String lastname="";
	private int balance;
	@Nullable
	private LLUUID reporthometo;

	public Agent(@Nonnull final JSLBot bot,
	             final Configuration c)
	{super(bot,c);}

	@Override
	public void loggedIn() {
		// get financials
		final MoneyBalanceRequest req=new MoneyBalanceRequest();
		req.bagentdata.vagentid=bot.getUUID();
		req.bagentdata.vsessionid=bot.getSession();
		req.bmoneydata.vtransactionid=new LLUUID();
		bot.send(req,true);
	}

	public void agentDataUpdateUDPImmediate(@Nonnull final UDPEvent event) {
		final AgentDataUpdate adu=(AgentDataUpdate) event.body();
		firstname=adu.bagentdata.vfirstname.toString();
		lastname=adu.bagentdata.vlastname.toString();
		groupname=adu.bagentdata.vgroupname.toString();
		grouptitle=adu.bagentdata.vgrouptitle.toString();

	}

	public void onlineNotificationUDPImmediate(@Nonnull final UDPEvent event) {
		final List<OnlineNotification_bAgentBlock> agents=((OnlineNotification) event.body()).bagentblock;
		for (final OnlineNotification_bAgentBlock block: agents) {
			final LLUUID uuid=block.vagentid;
			synchronized (online) {
				if (!online.contains(uuid)) {
					log.log(Level.INFO,
					        "Friend ONLINE: {0} ({1}) [{2}]",
					        new Object[]{bot.getDisplayName(uuid),bot.getUserName(uuid),uuid.toUUIDString()}
					       );
					online.add(uuid);
				}
			}
			synchronized (offline) { offline.remove(uuid); }
		}
	}

	public void offlineNotificationUDPImmediate(@Nonnull final UDPEvent event) {
		final List<OfflineNotification_bAgentBlock> agents=((OfflineNotification) event.body()).bagentblock;
		for (final OfflineNotification_bAgentBlock block: agents) {
			final LLUUID uuid=block.vagentid;
			synchronized (offline) {
				if (!offline.contains(uuid)) {
					log.log(Level.INFO,
					        "Friend offline: {0} ({1}) [{2}]",
					        new Object[]{bot.getDisplayName(uuid),bot.getUserName(uuid),uuid.toUUIDString()}
					       );
					offline.add(uuid);
				}
			}
			synchronized (online) { online.remove(uuid); }
		}
	}

	public void agentMovementCompleteUDPImmediate(@Nonnull final UDPEvent event) {
		final AgentMovementComplete amc=(AgentMovementComplete) event.body();
		bot.setPos(amc.bdata.vposition);
		bot.setLookAt(amc.bdata.vlookat);
		final U64 regionhandle=amc.bdata.vregionhandle;
		if (Debug.REGIONHANDLES) {
			log.fine("AgentMovementComplete discovers region handle "+Long.toUnsignedString(regionhandle.value));
		}
	}

	public void teleportLocalUDPImmediate(@Nonnull final UDPEvent event) {
		final TeleportLocal tp=(TeleportLocal) event.body();
		bot.setPos(tp.binfo.vposition);
		bot.setLookAt(tp.binfo.vlookat);
	}

	public void moneyBalanceReplyUDPImmediate(@Nonnull final UDPEvent event) {
		final MoneyBalanceReply money=(MoneyBalanceReply) event.body();
		balance=money.bmoneydata.vmoneybalance.value;
		final int sqmcredit=money.bmoneydata.vsquaremeterscredit.value;
		final int sqmspent=money.bmoneydata.vsquaremeterscommitted.value;
		final String description=money.bmoneydata.vdescription.toString();
		log.log(Level.INFO,
		        "Balance: {0}L$, Land: {1}m2/{2}m2 {3}",
		        new Object[]{balance,sqmspent,sqmcredit,description}
		       );
	}

	@Nonnull
	@CmdHelp(description="Returns some detailed packet accounting to the console")
	public String accountingCommand(@Nonnull final CommandEvent command) {
		command.bot().dumpAccounting();
		return "0 - See console for output";
	}

	@Nonnull
	@CmdHelp(description="Returns some basic information about the logged in agent")
	public String statusCommand(final CommandEvent command) {
		return "Agent is "+firstname+" "+lastname+"\n"+"("+grouptitle+" of "+groupname+")\n"+"Pos: "+bot.getPos()+"\n"+"Looking: "+bot
				.getLookAt()+"\n"+"Region: "+bot.getRegionName()+"\n"+"Bytes IN: "+bot.bytesin.get()+"    OUT: "+bot.bytesout
				.get()+"\n"+"BPS IN: "+(int) (((float) bot.bytesin.get())/((float) bot.getSecondsSinceStartup()))+"    OUT: "+(int) (((float) bot.bytesout
				.get())/((float) bot.getSecondsSinceStartup()));
	}

	@Nonnull
	@CmdHelp(description="Sets the FOV (field of view) to TWO_PI")
	public String fovMaxCommand(final CommandEvent command) {
		bot.setMaxFOV();
		return "Set";
	}

	@Nonnull
	@CmdHelp(description="Sets the FOV (field of view) to Zero")
	public String fovMinCommand(final CommandEvent command) {
		bot.setMinFOV();
		return "Set";
	}

	@Nonnull
	@CmdHelp(description="Send agent update")
	public String updateCommand(final CommandEvent command) {
		bot.agentUpdate();
		return "Sent";
	}

	@Nonnull
	@CmdHelp(description="Set agent's draw distance")
	public String drawdistanceCommand(final CommandEvent command,
	                                  @Nullable @ParamHelp(description="Meters draw distance") final String set)
	{
		if (set==null || set.isEmpty()) { return "0 - Draw distance is "+bot.drawDistance(); }
		bot.drawDistance(Float.parseFloat(set));
		return "0 - Draw Distance Set";
	}

	@Nonnull
	@CmdHelp(description="Attempt to blind the bot by setting camera out of scene")
	public String blindCommand(final CommandEvent command) {
		bot.blind();
		return "0 - Blinded";
	}

	@Nonnull
	@CmdHelp(description="Stop attempting to blind the bot")
	public String unblindCommand(final CommandEvent command) {
		bot.unblind();
		return "0 - Unblinded";
	}

	public void coarseLocationUpdateUDPDelayed(@Nonnull final UDPEvent event) {
		final CoarseLocationUpdate up=(CoarseLocationUpdate) event.body();
		final List<CoarseLocationUpdate_bLocation> locations=up.blocation;
		final List<CoarseLocationUpdate_bAgentData> agents=up.bagentdata;
		if (locations.size()!=agents.size()) {
			log.severe("Equal length co-ord/agent assumption violated");
			return;
		}
		final Map<LLUUID,LLVector3> locmap=new HashMap<>();
		for (int i=0;i<locations.size();i++) {
			final LLUUID agent=agents.get(i).vagentid;
			final LLVector3 location=new LLVector3();
			location.x=locations.get(i).vx.value;
			location.y=locations.get(i).vy.value;
			location.z=locations.get(i).vz.value;
			locmap.put(agent,location);
		}
		event.region().setCoarseAgentLocations(locmap);
	}

	@Nonnull
	@CmdHelp(description="Set the agent's start location")
	public String setHomeCommand(@Nonnull final CommandEvent event) {
		reporthometo=event.respondTo();
		final SetStartLocationRequest req=new SetStartLocationRequest(bot);
		req.bstartlocationdata.vsimname=new Variable1(bot.getRegionName());
		req.bstartlocationdata.vlocationid.value=1;
		req.bstartlocationdata.vlocationpos=bot.getPos();
		req.bstartlocationdata.vlocationlookat=bot.getLookAt();
		bot.send(req,true);
		return "0 - Set Home request sent";
	}

	public void alertMessageUDPDelayed(@Nonnull final UDPEvent event) {
		final AlertMessage a=(AlertMessage) event.body();
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

