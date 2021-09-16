package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class GroupVoteHistoryRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 361; }
	@Nonnull
    public final String getName() { return "GroupVoteHistoryRequest"; }
	@Nonnull
    @Sequence(0)
	public GroupVoteHistoryRequest_bAgentData bagentdata=new GroupVoteHistoryRequest_bAgentData();
	@Nonnull
    @Sequence(1)
	public GroupVoteHistoryRequest_bGroupData bgroupdata=new GroupVoteHistoryRequest_bGroupData();
	@Nonnull
    @Sequence(2)
	public GroupVoteHistoryRequest_bTransactionData btransactiondata=new GroupVoteHistoryRequest_bTransactionData();
	public GroupVoteHistoryRequest(){}
	public GroupVoteHistoryRequest(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
