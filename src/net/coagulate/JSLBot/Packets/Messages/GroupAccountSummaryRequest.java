package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class GroupAccountSummaryRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 353; }
	@Nonnull
    public final String getName() { return "GroupAccountSummaryRequest"; }
	@Nonnull
    @Sequence(0)
	public GroupAccountSummaryRequest_bAgentData bagentdata=new GroupAccountSummaryRequest_bAgentData();
	@Nonnull
    @Sequence(1)
	public GroupAccountSummaryRequest_bMoneyData bmoneydata=new GroupAccountSummaryRequest_bMoneyData();
	public GroupAccountSummaryRequest(){}
	public GroupAccountSummaryRequest(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
