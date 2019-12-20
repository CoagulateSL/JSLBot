package net.coagulate.JSLBot.Handlers;

import net.coagulate.JSLBot.*;
import net.coagulate.JSLBot.JSLBot.CmdHelp;
import net.coagulate.JSLBot.JSLBot.ParamHelp;
import net.coagulate.JSLBot.LLSD.*;
import net.coagulate.JSLBot.Packets.Messages.*;
import net.coagulate.JSLBot.Packets.Types.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Handle group related commands and messages.
 *
 * @author Iain Price
 */
public class Groups extends Handler {

	final Map<LLUUID,LLSDMap> groupmembership=new HashMap<>();
	private final Map<LLUUID,GroupData> groups=new HashMap<>();

	public Groups(@Nonnull final JSLBot bot,
	              final Configuration config) {
		super(bot,config);
	}

	@Nonnull
	@CmdHelp(description="Invite a user to a given group/role")
	public String groupInviteCommand(final CommandEvent command,
	                                 final String avataruuid,
	                                 final String groupuuid,
	                                 @Nullable final String roleuuid) {
		final LLUUID avatar=new LLUUID(avataruuid);
		final LLUUID group=new LLUUID(groupuuid);
		LLUUID role=new LLUUID();
		if (roleuuid!=null) { role=new LLUUID(roleuuid); }
		final InviteGroupRequest igr=new InviteGroupRequest();
		igr.bagentdata.vagentid=bot.getUUID();
		igr.bagentdata.vsessionid=bot.getSession();
		igr.bgroupdata.vgroupid=group;
		igr.binvitedata=new ArrayList<>();
		final InviteGroupRequest_bInviteData igrid=new InviteGroupRequest_bInviteData();
		igrid.vinviteeid=avatar;
		igrid.vroleid=role;
		igr.binvitedata.add(igrid);
		bot.send(igr,true);
		log.info("Sent group invite to "+avatar.toUUIDString()+" to group "+group.toUUIDString()+" role "+role.toUUIDString());
		return "Invite sent";
	}

	@Nonnull
	@CmdHelp(description="Eject a user from a given group/role")
	public String groupEjectCommand(final CommandEvent command,
	                                final String avataruuid,
	                                final String groupuuid) {
		final LLUUID avatar=new LLUUID(avataruuid);
		final LLUUID group=new LLUUID(groupuuid);
		//LLUUID role=new LLUUID(); if (roleuuid!=null) { role=new LLUUID(roleuuid); }
		final EjectGroupMemberRequest egr=new EjectGroupMemberRequest();
		egr.bagentdata.vagentid=bot.getUUID();
		egr.bagentdata.vsessionid=bot.getSession();
		egr.bgroupdata.vgroupid=group;
		egr.bejectdata=new ArrayList<>();
		final EjectGroupMemberRequest_bEjectData egrid=new EjectGroupMemberRequest_bEjectData();
		egrid.vejecteeid=avatar;
		egr.bejectdata.add(egrid);
		bot.send(egr,true);
		log.info("Ejected "+avatar.toUUIDString()+" from group "+group.toUUIDString());
		return "Ejection request sent";
	}

	@Nonnull
	@CmdHelp(description="List groups the logged in agent is a member of")
	public String groupsListCommand(final CommandEvent command) {
		final StringBuilder resp=new StringBuilder("Groups:");
		synchronized (groups) {
			for (final GroupData g: groups.values()) {
				resp.append("\n");
				resp.append(g.groupname).append(" (").append(g.uuid().toUUIDString()).append(") ");
				if (g.contribution!=0) { resp.append("Contribution:").append(g.contribution).append(" "); }
				if (g.listinprofile) { resp.append("Listed"); }
				else { resp.append("Unlisted"); }
			}
		}
		return resp.toString();
	}

	@Nullable
	private LLUUID findGroupUUID(@Nonnull final String name) {
		synchronized (groups) {
			for (final Map.Entry<LLUUID,GroupData> entry: groups.entrySet()) {
				final GroupData gd=entry.getValue();
				if (gd!=null) {
					if (name.equalsIgnoreCase(gd.groupname)) { return entry.getKey(); }
				}
			}
		}
		return null;
	}

	public void improvedInstantMessageUDPImmediate(@Nonnull final UDPEvent event) {
		final ImprovedInstantMessage m=(ImprovedInstantMessage) event.body();
		if (m.bmessageblock.vdialog.value==3) {
			final U32BE feeraw=new U32BE(ByteBuffer.wrap(m.bmessageblock.vbinarybucket.value));
			final int fee=feeraw.value;
			final LLUUID groupid=m.bmessageblock.vid;
			final Map<String,String> param=new HashMap<>();
			param.put("groupid",groupid.toString());
			final CommandEvent join=new CommandEvent(bot,event.region(),"acceptGroupInvites",param,null);
			join.invokerUsername(m.bmessageblock.vfromagentname.toString());
			final String reject=bot.brain().auth(join);
			byte num=35;
			if (fee>0) {
				num=36;
				log.warning("Rejected charged (L$"+fee+") invite to join group "+groupid.toUUIDString()+" from "+m.bmessageblock.vfromagentname);
			}
			if (num==35 && reject!=null) {
				log.info("Rejected invite to join group "+groupid.toUUIDString()+" from "+m.bmessageblock.vfromagentname);
				num=36;
			}
			final ImprovedInstantMessage im=new ImprovedInstantMessage();
			im.bagentdata.vagentid=bot.getUUID();
			im.bagentdata.vsessionid=bot.getSession();
			im.bmessageblock.vfromagentname=new Variable1(bot.getUsername());
			im.bmessageblock.vtoagentid=groupid;
			im.bmessageblock.vmessage=new Variable2();
			im.bmessageblock.vid=m.bmessageblock.vid;
			im.bmessageblock.vdialog=new U8(num);
			bot.send(im,true);
			log.info("Sent accept response to group invite to join group "+groupid.toUUIDString()+" from "+m.bmessageblock.vfromagentname);
		}
	}

	public void joinGroupReplyUDPImmediate(@Nonnull final UDPEvent event) {
		final JoinGroupReply jgr=(JoinGroupReply) event.body();
		final String groupid=jgr.bgroupdata.vgroupid.toUUIDString();
		if (jgr.bgroupdata.vsuccess.value!=0) {
			log.info("Joined group "+groupid);
		}
		else {
			log.warning("Failed to join group "+groupid);
		}
	}

	public void agentGroupDataUpdateXMLDelayed(@Nonnull final XMLEvent event) {
		final LLSDMap body=event.map();
		final LLSDArray groupslist=(LLSDArray) body.get("GroupData");
		synchronized (groups) {
			for (final Atomic groupatom: groupslist) {
				final LLSDMap group=(LLSDMap) groupatom;
				final String groupname=group.get("GroupName").toString();
				final LLSDBinary grouppowers=(LLSDBinary) group.get("GroupPowers");
				final boolean listinprofile=((LLSDBoolean) group.get("ListInProfile",new LLSDBoolean(true))).get();
				final boolean acceptnotices=((LLSDBoolean) group.get("AcceptNotices",new LLSDBoolean(true))).get();
				final int contribution=((LLSDInteger) group.get("Contribution")).get();
				LLUUID uuid=((LLSDUUID) group.get("GroupID")).toLLUUID();
				GroupData g=null;
				synchronized (groups) {
					for (final LLUUID compare: groups.keySet()) {
						if (compare.equals(uuid)) {
							g=groups.get(compare);
							uuid=compare;
						}
					}
					if (g==null) { g=new GroupData(); }
					g.groupname=groupname;
					g.grouppowers=grouppowers;
					g.listinprofile=listinprofile;
					g.acceptnotices=acceptnotices;
					g.contribution=contribution;
					g.uuid=uuid;
					groups.put(uuid,g);
				}
			}
		}
	}

	@Nullable
	public LLSDMap getMembership(final LLUUID uuid) {
		for (final Map.Entry<LLUUID,LLSDMap> entry: groupmembership.entrySet()) {
			if (entry.getKey().equals(uuid)) { return entry.getValue(); }
		}
		return null;
	}

	@CmdHelp(description="Collect a group's roster")
	public void groupRosterCommand(final CommandEvent command,
	                               final String uuid) throws IOException {
		final LLSDMap req=new LLSDMap();
		req.put("group_id",new LLSDUUID(uuid));
		final LLSD llsd=new LLSD(req);
		final LLSDMap response=bot.getCAPS().invokeCAPS("GroupMemberData","",llsd);
		if (response==null) { throw new IOException("GroupMemberData CAPS returned a null map"); }
		final LLSDMap members=(LLSDMap) response.get("members");
		if (members==null) { throw new NullPointerException("Failed to extract members map"); }
		groupmembership.put(new LLUUID(uuid),members);
	}

	@Nonnull
	@CmdHelp(description="Selects a group as active")
	public String activateGroupCommand(final CommandEvent event,
	                                   @Nullable @ParamHelp(description="Group UUID to activate (or zero UUID for none)") final String uuid,
	                                   @Nonnull @ParamHelp(description="Group name to activate, if UUID not supplied (supports NONE in upper case)") final String name) {
		LLUUID target;
		if (uuid==null || uuid.isEmpty()) {
			target=findGroupUUID(name);
		}
		else {
			target=new LLUUID(uuid);
		}
		if (target==null && "NONE".equals(name)) { target=new LLUUID(); }
		if (target==null) { return "1 - Failed to obtain target group UUID for '"+name+"'"; }
		final ActivateGroup req=new ActivateGroup(bot);
		req.bagentdata.vgroupid=target;
		bot.send(req,true);
		return "0 - Group activation requested";
	}

	public static class GroupData {
		@Nullable
		String groupname;
		@Nullable
		LLSDBinary grouppowers;
		boolean listinprofile=true;
		boolean acceptnotices=true;
		int contribution;
		@Nullable
		LLUUID uuid;

		@Nonnull
		public LLUUID uuid() {
			if (uuid==null) { throw new IllegalArgumentException("Group data has no UUID?"); }
			return uuid;
		}
	}

}
