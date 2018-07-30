package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class InviteGroupRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 349; }
	public final String getName() { return "InviteGroupRequest"; }
	@Sequence(0)
	public InviteGroupRequest_bAgentData bagentdata=new InviteGroupRequest_bAgentData();
	@Sequence(1)
	public InviteGroupRequest_bGroupData bgroupdata=new InviteGroupRequest_bGroupData();
	@Sequence(2)
	public List<InviteGroupRequest_bInviteData> binvitedata;
}
