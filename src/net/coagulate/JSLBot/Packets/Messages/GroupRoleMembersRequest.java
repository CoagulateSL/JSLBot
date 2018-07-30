package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GroupRoleMembersRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 373; }
	public final String getName() { return "GroupRoleMembersRequest"; }
	@Sequence(0)
	public GroupRoleMembersRequest_bAgentData bagentdata=new GroupRoleMembersRequest_bAgentData();
	@Sequence(1)
	public GroupRoleMembersRequest_bGroupData bgroupdata=new GroupRoleMembersRequest_bGroupData();
}
