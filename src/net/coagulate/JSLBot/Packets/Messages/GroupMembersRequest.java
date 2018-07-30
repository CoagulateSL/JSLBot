package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GroupMembersRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 366; }
	public final String getName() { return "GroupMembersRequest"; }
	@Sequence(0)
	public GroupMembersRequest_bAgentData bagentdata=new GroupMembersRequest_bAgentData();
	@Sequence(1)
	public GroupMembersRequest_bGroupData bgroupdata=new GroupMembersRequest_bGroupData();
}
