package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GroupRoleMembersReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 374; }
	public final String getName() { return "GroupRoleMembersReply"; }
	@Sequence(0)
	public GroupRoleMembersReply_bAgentData bagentdata=new GroupRoleMembersReply_bAgentData();
	@Sequence(1)
	public List<GroupRoleMembersReply_bMemberData> bmemberdata;
	public GroupRoleMembersReply(){}
	public GroupRoleMembersReply(JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
