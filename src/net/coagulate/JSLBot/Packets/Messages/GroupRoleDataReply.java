package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GroupRoleDataReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 372; }
	public final String getName() { return "GroupRoleDataReply"; }
	@Sequence(0)
	public GroupRoleDataReply_bAgentData bagentdata=new GroupRoleDataReply_bAgentData();
	@Sequence(1)
	public GroupRoleDataReply_bGroupData bgroupdata=new GroupRoleDataReply_bGroupData();
	@Sequence(2)
	public List<GroupRoleDataReply_bRoleData> broledata;
}
