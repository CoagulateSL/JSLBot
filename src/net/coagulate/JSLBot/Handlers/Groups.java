package net.coagulate.JSLBot.Handlers;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.coagulate.JSLBot.CommandEvent;
import net.coagulate.JSLBot.Configuration;
import net.coagulate.JSLBot.Handler;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.JSLBot.CmdHelp;
import net.coagulate.JSLBot.JSLBot.ParamHelp;
import net.coagulate.JSLBot.LLSD.LLSD;
import net.coagulate.JSLBot.LLSD.LLSDArray;
import net.coagulate.JSLBot.LLSD.LLSDBinary;
import net.coagulate.JSLBot.LLSD.LLSDBoolean;
import net.coagulate.JSLBot.LLSD.LLSDInteger;
import net.coagulate.JSLBot.LLSD.LLSDMap;
import net.coagulate.JSLBot.LLSD.LLSDString;
import net.coagulate.JSLBot.LLSD.LLSDUUID;
import net.coagulate.JSLBot.Packets.Messages.ActivateGroup;
import net.coagulate.JSLBot.Packets.Messages.EjectGroupMemberRequest;
import net.coagulate.JSLBot.Packets.Messages.EjectGroupMemberRequest_bEjectData;
import net.coagulate.JSLBot.Packets.Messages.ImprovedInstantMessage;
import net.coagulate.JSLBot.Packets.Messages.InviteGroupRequest;
import net.coagulate.JSLBot.Packets.Messages.InviteGroupRequest_bInviteData;
import net.coagulate.JSLBot.Packets.Messages.JoinGroupReply;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.U32BE;
import net.coagulate.JSLBot.Packets.Types.U8;
import net.coagulate.JSLBot.Packets.Types.Variable1;
import net.coagulate.JSLBot.Packets.Types.Variable2;
import net.coagulate.JSLBot.UDPEvent;
import net.coagulate.JSLBot.XMLEvent;

/** Handle group related commands and messages.
 *
 * @author Iain Price
 */
public class Groups extends Handler {

    public Groups(JSLBot bot,Configuration config) {
        super(bot,config);
    }

    @CmdHelp(description = "Invite a user to a given group/role")
    public String groupInviteCommand(CommandEvent command,String avataruuid,String groupuuid,String roleuuid) {
        LLUUID avatar=new LLUUID(avataruuid);
        LLUUID group=new LLUUID(groupuuid);
        LLUUID role=new LLUUID(); if (roleuuid!=null) { role=new LLUUID(roleuuid); }
        InviteGroupRequest igr=new InviteGroupRequest();
        igr.bagentdata.vagentid=bot.getUUID();
        igr.bagentdata.vsessionid=bot.getSession();
        igr.bgroupdata.vgroupid=group;
        igr.binvitedata=new ArrayList<>();
        InviteGroupRequest_bInviteData igrid=new InviteGroupRequest_bInviteData();
        igrid.vinviteeid=avatar;
        igrid.vroleid=role;
        igr.binvitedata.add(igrid);
        bot.send(igr,true);
        log.info("Sent group invite to "+avatar.toUUIDString()+" to group "+group.toUUIDString()+" role "+role.toUUIDString());
        return "Invite sent";
    }

    @CmdHelp(description = "Eject a user from a given group/role")
    public String groupEjectCommand(CommandEvent command,String avataruuid,String groupuuid) {
        LLUUID avatar=new LLUUID(avataruuid);
        LLUUID group=new LLUUID(groupuuid);
        //LLUUID role=new LLUUID(); if (roleuuid!=null) { role=new LLUUID(roleuuid); }
        EjectGroupMemberRequest egr=new EjectGroupMemberRequest();
        egr.bagentdata.vagentid=bot.getUUID();
        egr.bagentdata.vsessionid=bot.getSession();
        egr.bgroupdata.vgroupid=group;
        egr.bejectdata=new ArrayList<>();
        EjectGroupMemberRequest_bEjectData egrid=new EjectGroupMemberRequest_bEjectData();
        egrid.vejecteeid=avatar;
        egr.bejectdata.add(egrid);
        bot.send(egr,true);
        log.info("Ejected "+avatar.toUUIDString()+" from group "+group.toUUIDString());
        return "Ejection request sent";
    }
    
    @CmdHelp(description="List groups the logged in agent is a member of")
    public String groupsListCommand(CommandEvent command) {
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
        

    
    private final Map<LLUUID,GroupData> groups=new HashMap<>();

    private LLUUID findGroupUUID(String name) {
        synchronized(groups) {
            for (LLUUID uuid:groups.keySet()) {
                GroupData gd=groups.get(uuid);
                if (gd!=null) {
                    if (gd.groupname.equalsIgnoreCase(name)) { return uuid; }
                }
            }
        }
        return null;
    }
    
    public void improvedInstantMessageUDPImmediate(UDPEvent event) {
        ImprovedInstantMessage m=(ImprovedInstantMessage)event.body();
        if (m.bmessageblock.vdialog.value==3) {
            U32BE feeraw=new U32BE(ByteBuffer.wrap(m.bmessageblock.vbinarybucket.value));
            int fee=feeraw.value;
            LLUUID groupid=m.bmessageblock.vid;
            Map<String,String> param=new HashMap<>();
            param.put("groupid",groupid.toString());
            CommandEvent join=new CommandEvent(bot, event.region(), "acceptGroupInvites", param, null);
            join.invokerUsername(m.bmessageblock.vfromagentname.toString());
            String reject=bot.brain().auth(join);
            byte num=35;
            if (fee>0) { num=36; log.warning("Rejected charged (L$"+fee+") invite to join group "+groupid.toUUIDString()+" from "+m.bmessageblock.vfromagentname.toString()); }
            if (num==35 && reject!=null) { log.info("Rejected invite to join group "+groupid.toUUIDString()+" from "+m.bmessageblock.vfromagentname.toString()); num=36;}
            ImprovedInstantMessage im=new ImprovedInstantMessage();
            im.bagentdata.vagentid=bot.getUUID();
            im.bagentdata.vsessionid=bot.getSession();
            im.bmessageblock.vfromagentname=new Variable1(bot.getUsername());
            im.bmessageblock.vtoagentid=groupid;
            im.bmessageblock.vmessage=new Variable2();
            im.bmessageblock.vid=m.bmessageblock.vid;
            im.bmessageblock.vdialog=new U8(num);
            bot.send(im,true);
            log.info("Sent accept response to group invite to join group "+groupid.toUUIDString()+" from "+m.bmessageblock.vfromagentname.toString());
        }
    }
    
    public void joinGroupReplyUDPImmediate(UDPEvent event) {
        JoinGroupReply jgr=(JoinGroupReply)event.body();
        String groupid=jgr.bgroupdata.vgroupid.toUUIDString();
        if (jgr.bgroupdata.vsuccess.value!=0) {
            log.info("Joined group "+groupid);
        } else {
            log.warning("Failed to join group "+groupid);
        }
    }

    public void agentGroupDataUpdateXMLDelayed(XMLEvent event) {
        LLSDMap body=event.map();
        LLSDArray groupslist=(LLSDArray) body.get("GroupData");
        synchronized(groups) {
            for (Iterator it = groupslist.iterator(); it.hasNext();) {
                LLSDMap group = (LLSDMap) it.next();
                String groupname=((LLSDString)(group.get("GroupName"))).toString();
                LLSDBinary grouppowers=(LLSDBinary) group.get("GroupPowers");
                boolean listinprofile=((LLSDBoolean)group.get("ListInProfile",new LLSDBoolean(true))).get();
                boolean acceptnotices=((LLSDBoolean)group.get("AcceptNotices",new LLSDBoolean(true))).get();
                int contribution=((LLSDInteger)group.get("Contribution")).get();
                LLUUID uuid=((LLSDUUID)group.get("GroupID")).toLLUUID();
                GroupData g=null;
                synchronized(groups) { 
                    for(LLUUID compare:groups.keySet()) {
                        if (compare.equals(uuid)) { g=groups.get(compare); uuid=compare; }
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
        

    Map<LLUUID,LLSDMap> groupmembership=new HashMap<>();
    public LLSDMap getMembership(LLUUID uuid) {
        for (LLUUID compare:groupmembership.keySet()) { if (compare.equals(uuid)) { return groupmembership.get(compare); } }
        return null;
    }
    @CmdHelp(description="Collect a group's roster")
    public void groupRosterCommand(CommandEvent command,String uuid) throws IOException {
        LLSDMap req=new LLSDMap();
        req.put("group_id",new LLSDUUID(uuid));
        LLSD llsd=new LLSD(req);
        LLSDMap response = bot.getCAPS().invokeCAPS("GroupMemberData", "", llsd);
        LLSDMap members=(LLSDMap) response.get("members");
        if (members==null) { throw new NullPointerException("Failed to extract members map"); }
        groupmembership.put(new LLUUID(uuid),members);
    }
    public class GroupData {
        String groupname=null;
        LLSDBinary grouppowers=null;
        boolean listinprofile=true;
        boolean acceptnotices=true;
        int contribution=0;
        LLUUID uuid=null;
    }
    
    @CmdHelp(description="Selects a group as active")
    public String activateGroupCommand(CommandEvent event,
            @ParamHelp(description="Group UUID to activate (or zero UUID for none)")
            String uuid,
            @ParamHelp(description="Group name to activate, if UUID not supplied (supports NONE in upper case)")
            String name)
    {
        if (uuid==null && name==null) { return "2 - You must supply a group uuid or name"; }
        LLUUID target;
        if (uuid==null || uuid.isEmpty()) {
            target=findGroupUUID(name);
        } else {
            target=new LLUUID(uuid);
        }
        if (target==null && name!=null && "NONE".equals(name)) { target=new LLUUID(); }
        if (target==null) { return "1 - Failed to obtain target group UUID for '"+name+"'"; }
        ActivateGroup req=new ActivateGroup(bot);
        req.bagentdata.vgroupid=target;
        bot.send(req,true);
        return "0 - Group activation requested";
    }
    
}
