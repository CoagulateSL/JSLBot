package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class GroupVoteHistoryItemReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 362; }
	@Nonnull
    public final String getName() { return "GroupVoteHistoryItemReply"; }
	@Nonnull
    @Sequence(0)
	public GroupVoteHistoryItemReply_bAgentData bagentdata=new GroupVoteHistoryItemReply_bAgentData();
	@Nonnull
    @Sequence(1)
	public GroupVoteHistoryItemReply_bTransactionData btransactiondata=new GroupVoteHistoryItemReply_bTransactionData();
	@Nonnull
    @Sequence(2)
	public GroupVoteHistoryItemReply_bHistoryItemData bhistoryitemdata=new GroupVoteHistoryItemReply_bHistoryItemData();
	@Sequence(3)
	public List<GroupVoteHistoryItemReply_bVoteItem> bvoteitem;
	public GroupVoteHistoryItemReply(){}
	public GroupVoteHistoryItemReply(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
