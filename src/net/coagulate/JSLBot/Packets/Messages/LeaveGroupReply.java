package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class LeaveGroupReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 348; }
	@Nonnull
    public final String getName() { return "LeaveGroupReply"; }
	@Nonnull
    @Sequence(0)
	public LeaveGroupReply_bAgentData bagentdata=new LeaveGroupReply_bAgentData();
	@Nonnull
    @Sequence(1)
	public LeaveGroupReply_bGroupData bgroupdata=new LeaveGroupReply_bGroupData();
	public LeaveGroupReply(){}
	public LeaveGroupReply(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
