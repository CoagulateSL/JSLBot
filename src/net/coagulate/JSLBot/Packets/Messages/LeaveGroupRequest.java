package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class LeaveGroupRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 347; }
	@Nonnull
    public final String getName() { return "LeaveGroupRequest"; }
	@Nonnull
    @Sequence(0)
	public LeaveGroupRequest_bAgentData bagentdata=new LeaveGroupRequest_bAgentData();
	@Nonnull
    @Sequence(1)
	public LeaveGroupRequest_bGroupData bgroupdata=new LeaveGroupRequest_bGroupData();
	public LeaveGroupRequest(){}
	public LeaveGroupRequest(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
