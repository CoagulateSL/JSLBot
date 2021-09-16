package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class InviteGroupRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 349; }
	@Nonnull
    public final String getName() { return "InviteGroupRequest"; }
	@Nonnull
    @Sequence(0)
	public InviteGroupRequest_bAgentData bagentdata=new InviteGroupRequest_bAgentData();
	@Nonnull
    @Sequence(1)
	public InviteGroupRequest_bGroupData bgroupdata=new InviteGroupRequest_bGroupData();
	@Sequence(2)
	public List<InviteGroupRequest_bInviteData> binvitedata;
	public InviteGroupRequest(){}
	public InviteGroupRequest(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
