package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class InviteGroupResponse extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 350; }
	@Nonnull
    public final String getName() { return "InviteGroupResponse"; }
	@Nonnull
    @Sequence(0)
	public InviteGroupResponse_bInviteData binvitedata=new InviteGroupResponse_bInviteData();
	@Nonnull
    @Sequence(1)
	public InviteGroupResponse_bGroupData bgroupdata=new InviteGroupResponse_bGroupData();
}
