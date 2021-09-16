package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class GroupActiveProposalItemReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 360; }
	@Nonnull
    public final String getName() { return "GroupActiveProposalItemReply"; }
	@Nonnull
    @Sequence(0)
	public GroupActiveProposalItemReply_bAgentData bagentdata=new GroupActiveProposalItemReply_bAgentData();
	@Nonnull
    @Sequence(1)
	public GroupActiveProposalItemReply_bTransactionData btransactiondata=new GroupActiveProposalItemReply_bTransactionData();
	@Sequence(2)
	public List<GroupActiveProposalItemReply_bProposalData> bproposaldata;
	public GroupActiveProposalItemReply(){}
	public GroupActiveProposalItemReply(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
