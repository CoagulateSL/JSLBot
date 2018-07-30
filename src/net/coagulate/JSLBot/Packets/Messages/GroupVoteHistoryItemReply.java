package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GroupVoteHistoryItemReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 362; }
	public final String getName() { return "GroupVoteHistoryItemReply"; }
	@Sequence(0)
	public GroupVoteHistoryItemReply_bAgentData bagentdata=new GroupVoteHistoryItemReply_bAgentData();
	@Sequence(1)
	public GroupVoteHistoryItemReply_bTransactionData btransactiondata=new GroupVoteHistoryItemReply_bTransactionData();
	@Sequence(2)
	public GroupVoteHistoryItemReply_bHistoryItemData bhistoryitemdata=new GroupVoteHistoryItemReply_bHistoryItemData();
	@Sequence(3)
	public List<GroupVoteHistoryItemReply_bVoteItem> bvoteitem;
}
