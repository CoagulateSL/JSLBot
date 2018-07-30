package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class EjectGroupMemberRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 345; }
	public final String getName() { return "EjectGroupMemberRequest"; }
	@Sequence(0)
	public EjectGroupMemberRequest_bAgentData bagentdata=new EjectGroupMemberRequest_bAgentData();
	@Sequence(1)
	public EjectGroupMemberRequest_bGroupData bgroupdata=new EjectGroupMemberRequest_bGroupData();
	@Sequence(2)
	public List<EjectGroupMemberRequest_bEjectData> bejectdata;
}
