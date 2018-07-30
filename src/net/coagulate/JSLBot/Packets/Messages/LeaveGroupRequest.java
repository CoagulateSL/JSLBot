package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class LeaveGroupRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 347; }
	public final String getName() { return "LeaveGroupRequest"; }
	@Sequence(0)
	public LeaveGroupRequest_bAgentData bagentdata=new LeaveGroupRequest_bAgentData();
	@Sequence(1)
	public LeaveGroupRequest_bGroupData bgroupdata=new LeaveGroupRequest_bGroupData();
}
