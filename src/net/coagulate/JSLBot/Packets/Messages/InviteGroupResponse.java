package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class InviteGroupResponse extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 350; }
	public final String getName() { return "InviteGroupResponse"; }
	@Sequence(0)
	public InviteGroupResponse_bInviteData binvitedata=new InviteGroupResponse_bInviteData();
	@Sequence(1)
	public InviteGroupResponse_bGroupData bgroupdata=new InviteGroupResponse_bGroupData();
}
