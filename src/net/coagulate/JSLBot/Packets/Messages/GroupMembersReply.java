package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GroupMembersReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 367; }
	public final String getName() { return "GroupMembersReply"; }
	@Sequence(0)
	public GroupMembersReply_bAgentData bagentdata=new GroupMembersReply_bAgentData();
	@Sequence(1)
	public GroupMembersReply_bGroupData bgroupdata=new GroupMembersReply_bGroupData();
	@Sequence(2)
	public List<GroupMembersReply_bMemberData> bmemberdata;
}
