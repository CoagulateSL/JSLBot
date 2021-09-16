package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class GroupAccountSummaryReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 354; }
	@Nonnull
    public final String getName() { return "GroupAccountSummaryReply"; }
	@Nonnull
    @Sequence(0)
	public GroupAccountSummaryReply_bAgentData bagentdata=new GroupAccountSummaryReply_bAgentData();
	@Nonnull
    @Sequence(1)
	public GroupAccountSummaryReply_bMoneyData bmoneydata=new GroupAccountSummaryReply_bMoneyData();
	public GroupAccountSummaryReply(){}
	public GroupAccountSummaryReply(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
