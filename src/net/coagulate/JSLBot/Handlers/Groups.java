package net.coagulate.JSLBot.Handlers;

import net.coagulate.JSLBot.*;
import net.coagulate.JSLBot.JSLBot.CmdHelp;
import net.coagulate.JSLBot.JSLBot.Param;
import net.coagulate.JSLBot.LLSD.*;
import net.coagulate.JSLBot.Packets.Messages.*;
import net.coagulate.JSLBot.Packets.Types.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.*;

/**
 * Handle group related commands and messages.
 *
 * @author Iain Price
 */
public class Groups extends Handler {
	
	final         Map<LLUUID,LLSDMap>      groupmembership=new HashMap<>();
	private final Map<LLUUID,GroupData>    groups         =new HashMap<>();
	private final Map<Long,Set<GroupRole>> grouproles     =new HashMap<>();
	
	public Groups(@Nonnull final JSLBot bot,final Configuration config) {
		super(bot,config);
	}
	
	// ---------- INSTANCE ----------
	@Nonnull
	@CmdHelp(description="Invite a user to a given group/role")
	public String groupInviteCommand(final CommandEvent command,
	                                 @Param(name="avataruuid", description="Avatar UUID to invite to group")
	                                 final String avataruuid,
	                                 @Param(name="groupuuid", description="Group UUID to invite avatar to")
	                                 final String groupuuid,
	                                 @Param(name="roleuuid", description="Role UUID within group to invite avatar to")
	                                 @Nullable final String roleuuid) {
		@Nonnull final LLUUID avatar=new LLUUID(avataruuid);
		@Nonnull final LLUUID group=new LLUUID(groupuuid);
		@Nullable LLUUID role=new LLUUID();
		if (roleuuid!=null) {
			role=new LLUUID(roleuuid);
		}
		@Nonnull final InviteGroupRequest igr=new InviteGroupRequest();
		igr.bagentdata.vagentid=bot.getUUID();
		igr.bagentdata.vsessionid=bot.getSession();
		igr.bgroupdata.vgroupid=group;
		igr.binvitedata=new ArrayList<>();
		@Nonnull final InviteGroupRequest_bInviteData igrid=new InviteGroupRequest_bInviteData();
		igrid.vinviteeid=avatar;
		igrid.vroleid=role;
		igr.binvitedata.add(igrid);
		bot.send(igr,true);
		log.info("Sent group invite to "+avatar.toUUIDString()+" to group "+group.toUUIDString()+" role "+
		         role.toUUIDString());
		return "Invite sent";
	}
	
	@Nonnull
	@CmdHelp(description="Eject a user from a given group/role")
	public String groupEjectCommand(final CommandEvent command,
	                                @Param(name="avataruuid", description="Avatar UUID to eject from group")
	                                final String avataruuid,
	                                @Param(name="groupuuid", description="Group UUID to eject from")
	                                final String groupuuid) {
		@Nonnull final LLUUID avatar=new LLUUID(avataruuid);
		@Nonnull final LLUUID group=new LLUUID(groupuuid);
		//LLUUID role=new LLUUID(); if (roleuuid!=null) { role=new LLUUID(roleuuid); }
		@Nonnull final EjectGroupMemberRequest egr=new EjectGroupMemberRequest();
		egr.bagentdata.vagentid=bot.getUUID();
		egr.bagentdata.vsessionid=bot.getSession();
		egr.bgroupdata.vgroupid=group;
		egr.bejectdata=new ArrayList<>();
		@Nonnull final EjectGroupMemberRequest_bEjectData egrid=new EjectGroupMemberRequest_bEjectData();
		egrid.vejecteeid=avatar;
		egr.bejectdata.add(egrid);
		bot.send(egr,true);
		log.info("Ejected "+avatar.toUUIDString()+" from group "+group.toUUIDString());
		return "Ejection request sent";
	}
	
	@Nonnull
	@CmdHelp(description="List groups the logged in agent is a member of")
	public String groupsListCommand(final CommandEvent command) {
		@Nonnull final StringBuilder resp=new StringBuilder("Groups:");
		synchronized(groups) {
			for (@Nonnull final GroupData g: groups.values()) {
				resp.append("\n");
				resp.append(g.groupname).append(" (").append(g.uuid().toUUIDString()).append(") ");
				if (g.contribution!=0) {
					resp.append("Contribution:").append(g.contribution).append(" ");
				}
				if (g.listinprofile) {
					resp.append("Listed");
				} else {
					resp.append("Unlisted");
				}
			}
		}
		return resp.toString();
	}
	
	public void improvedInstantMessageUDPImmediate(@Nonnull final UDPEvent event) {
		@Nonnull final ImprovedInstantMessage m=(ImprovedInstantMessage)event.body();
		if (m.bmessageblock.vdialog.value==3) {
			@Nonnull final U32BE feeraw=new U32BE(ByteBuffer.wrap(m.bmessageblock.vbinarybucket.value));
			final int fee=feeraw.value;
			final LLUUID groupid=m.bmessageblock.vid;
			@Nonnull final Map<String,String> param=new HashMap<>();
			param.put("groupid",groupid.toString());
			@Nullable final CommandEvent join=new CommandEvent(bot,event.region(),"acceptGroupInvites",param,null);
			join.invokerUsername(m.bmessageblock.vfromagentname.toString());
			@Nullable final String reject=bot.brain().auth(join);
			byte num=35;
			if (fee>0) {
				num=36;
				log.warning("Rejected charged (L$"+fee+") invite to join group "+groupid.toUUIDString()+" from "+
				            m.bmessageblock.vfromagentname);
			}
			if (num==35&&reject!=null) {
				log.info("Rejected invite to join group "+groupid.toUUIDString()+" from "+
				         m.bmessageblock.vfromagentname);
				num=36;
			}
			@Nonnull final ImprovedInstantMessage im=new ImprovedInstantMessage();
			im.bagentdata.vagentid=bot.getUUID();
			im.bagentdata.vsessionid=bot.getSession();
			im.bmessageblock.vfromagentname=new Variable1(bot.getUsername());
			im.bmessageblock.vtoagentid=groupid;
			im.bmessageblock.vmessage=new Variable2();
			im.bmessageblock.vid=m.bmessageblock.vid;
			im.bmessageblock.vdialog=new U8(num);
			bot.send(im,true);
			log.info("Sent accept response to group invite to join group "+groupid.toUUIDString()+" from "+
			         m.bmessageblock.vfromagentname);
		}
	}
	
	public void joinGroupReplyUDPImmediate(@Nonnull final UDPEvent event) {
		@Nonnull final JoinGroupReply jgr=(JoinGroupReply)event.body();
		@Nonnull final String groupid=jgr.bgroupdata.vgroupid.toUUIDString();
		if (jgr.bgroupdata.vsuccess.value==0) {
			log.warning("Failed to join group "+groupid);
		} else {
			log.info("Joined group "+groupid);
		}
	}
	
	public void groupRoleDataReplyUDPImmediate(@Nonnull final UDPEvent event) {
		@Nonnull final GroupRoleDataReply reply=(GroupRoleDataReply)event.body();
		// only processing the role data at the moment!
		final long group=reply.bgroupdata.vgroupid.toLong();
		@Nonnull final Set<GroupRole> roles=new HashSet<>();
		for (@Nonnull final GroupRoleDataReply_bRoleData role: reply.broledata) {
			roles.add(new GroupRole(role.vroleid,
			                        role.vname.toString(),
			                        role.vtitle.toString(),
			                        role.vdescription.toString(),
			                        role.vpowers.value,
			                        role.vmembers.value));
		}
		@Nullable Long signalObject=null;
		for (final Long potential: grouproles.keySet()) {
			if (potential==group) {
				signalObject=potential;
			}
		}
		grouproles.put(group,roles);
		if (signalObject!=null) {
			// the inspection warning is correct, but we pulled ourReference from a shared map into a local variable
			// (partly because we're about to replace it in the map we pulled it from)
			//noinspection SynchronizationOnLocalVariableOrMethodParameter
			synchronized(signalObject) {
				signalObject.notifyAll();
			}
		}
	}
	
	@Nonnull
	@CmdHelp(description="Returns a list of a group's roles")
	public String groupRolesCommand(final CommandEvent event,
	                                @Nullable @Param(name="uuid", description="Group UUID to query") final String uuid,
	                                @Nullable
	                                @Param(name="name", description="Group name to query, if UUID not supplied")
	                                final String name) {
		if (uuid==null&&name==null) {
			return "1 - No group parameter supplied";
		}
		@Nullable final LLUUID target;
		if (uuid==null||uuid.isEmpty()) {
			if (name==null) {
				return "1 - No group parameter supplied";
			}
			target=findGroupUUID(name);
		} else {
			target=new LLUUID(uuid);
		}
		if (target==null) {
			return "1 - Failed to obtain target group UUID for '"+name+"'";
		}
		@Nonnull final GroupRoleDataRequest req=new GroupRoleDataRequest(bot);
		final Long ourReference=target.toLong();
		grouproles.put(ourReference,null);
		@Nonnull final LLUUID requestID=LLUUID.random();
		req.bgroupdata.vgroupid=target;
		req.bgroupdata.vrequestid=requestID;
		bot.send(req,true);
		// the warning is correct, but, we stashed the local scoped variable in the map
		//noinspection SynchronizationOnLocalVariableOrMethodParameter
		synchronized(ourReference) {
			try {
				ourReference.wait(15000);
			} catch (final InterruptedException e) {
				return "1 - Wait was interrupted";
			}
		}
		if (grouproles.get(ourReference)==null) {
			return "1 - No response received within timeout";
		}
		@Nonnull final StringBuilder reply=
				new StringBuilder("Group Roles for group UUID ").append(target.toUUIDString());
		for (@Nonnull final GroupRole role: grouproles.get(ourReference)) {
			reply.append("\n").append(role.name).append(" - ").append(role.roleID.toUUIDString());
		}
		return reply.toString();
	}
	
	// ----- Internal Instance -----
	@Nullable
	private LLUUID findGroupUUID(@Nonnull final String name) {
		synchronized(groups) {
			for (@Nonnull final Map.Entry<LLUUID,GroupData> entry: groups.entrySet()) {
				final GroupData gd=entry.getValue();
				if (gd!=null) {
					if (name.equalsIgnoreCase(gd.groupname)) {
						return entry.getKey();
					}
				}
			}
		}
		return null;
	}
	
	public void agentGroupDataUpdateXMLDelayed(@Nonnull final XMLEvent event) {
		@Nonnull final LLSDMap body=event.map();
		@Nonnull final LLSDArray groupslist=(LLSDArray)body.get("GroupData");
		synchronized(groups) {
			for (final Atomic groupatom: groupslist) {
				@Nonnull final LLSDMap group=(LLSDMap)groupatom;
				final String groupname=group.get("GroupName").toString();
				@Nonnull final LLSDBinary grouppowers=(LLSDBinary)group.get("GroupPowers");
				final boolean listinprofile=((LLSDBoolean)group.get("ListInProfile",new LLSDBoolean(true))).get();
				final boolean acceptnotices=((LLSDBoolean)group.get("AcceptNotices",new LLSDBoolean(true))).get();
				final int contribution=((LLSDInteger)group.get("Contribution")).get();
				LLUUID uuid=((LLSDUUID)group.get("GroupID")).toLLUUID();
				@Nullable GroupData g=null;
				synchronized(groups) {
					for (@Nonnull final LLUUID compare: groups.keySet()) {
						if (compare.equals(uuid)) {
							g=groups.get(compare);
							uuid=compare;
						}
					}
					if (g==null) {
						g=new GroupData();
					}
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
	
	@Nonnull
	public Set<LLUUID> getMembers(final String groupuuid) {
		return getMembers(new LLUUID(groupuuid));
	}
	
	@Nonnull
	public Set<LLUUID> getMembers(final LLUUID groupuuid) {
		@Nullable final LLSDMap members=getMembership(groupuuid);
		@Nonnull final Set<LLUUID> ret=new HashSet<>();
		if (members==null) {
			throw new IllegalStateException("Failed to get group roster for some reason");
		}
		for (final String member: members.keys()) {
			@Nonnull final LLUUID uuid=new LLUUID(member);
			ret.add(uuid);
		}
		return ret;
	}
	
	@Nullable
	public LLSDMap getMembership(final LLUUID uuid) {
		for (@Nonnull final Map.Entry<LLUUID,LLSDMap> entry: groupmembership.entrySet()) {
			if (entry.getKey().equals(uuid)) {
				return entry.getValue();
			}
		}
		return null;
	}
	
	public boolean isMember(@Nonnull final LLUUID avatar,final LLUUID group) {
		for (final LLUUID member: getMembers(group)) {
			if (avatar.equals(member)) {
				return true;
			}
		}
		return false;
	}
	
	@CmdHelp(description="Collect a group's roster")
	public void groupRosterCommand(final CommandEvent command,
	                               @Nonnull @Param(name="uuid", description="Group UUID to get roster for")
	                               final String uuid) throws IOException {
		@Nonnull final LLSDMap req=new LLSDMap();
		req.put("group_id",new LLSDUUID(uuid));
		@Nonnull final LLSD llsd=new LLSD(req);
		@Nullable final LLSDMap response=bot.getCAPS().invokeCAPS("GroupMemberData","",llsd);
		if (response==null) {
			throw new IOException("GroupMemberData CAPS returned a null map");
		}
		@Nonnull final LLSDMap members=(LLSDMap)response.get("members");
		if (members==null) {
			throw new NullPointerException("Failed to extract members map");
		}
		groupmembership.put(new LLUUID(uuid),members);
	}
	
	@Nonnull
	@CmdHelp(description="Selects a group as active")
	public String activateGroupCommand(final CommandEvent event,
	                                   @Nullable
	                                   @Param(name="uuid", description="Group UUID to activate (or zero UUID for none)")
	                                   final String uuid,
	                                   @Nullable
	                                   @Param(name="name",
	                                          description="Group name to activate, if UUID not supplied (supports NONE in upper case)")
	                                   final String name) {
		@Nullable LLUUID target;
		if (uuid==null||uuid.isEmpty()) {
			if (name==null) {
				return "1 - No group parameter supplied";
			}
			target=findGroupUUID(name);
		} else {
			target=new LLUUID(uuid);
		}
		if (target==null&&"NONE".equals(name)) {
			target=new LLUUID();
		}
		if (target==null) {
			return "1 - Failed to obtain target group UUID for '"+name+"'";
		}
		@Nonnull final ActivateGroup req=new ActivateGroup(bot);
		req.bagentdata.vgroupid=target;
		bot.send(req,true);
		return "0 - Group activation requested";
	}
	
	public static class GroupRole {
		public final LLUUID roleID;
		public final String name;
		public final String title;
		public final String description;
		public final long   powers;
		public final int    members;
		
		public GroupRole(final LLUUID roleID,
		                 final String name,
		                 final String title,
		                 final String description,
		                 final long powers,
		                 final int members) {
			this.roleID=roleID;
			this.name=name;
			this.title=title;
			this.description=description;
			this.powers=powers;
			this.members=members;
		}
	}
	
	public static class GroupData {
		@Nullable String     groupname;
		@Nullable LLSDBinary grouppowers;
		boolean listinprofile=true;
		boolean acceptnotices=true;
		int     contribution;
		@Nullable LLUUID uuid;
		
		// ---------- INSTANCE ----------
		@Nonnull
		public LLUUID uuid() {
			if (uuid==null) {
				throw new IllegalArgumentException("Group data has no UUID?");
			}
			return uuid;
		}
	}
	
}
