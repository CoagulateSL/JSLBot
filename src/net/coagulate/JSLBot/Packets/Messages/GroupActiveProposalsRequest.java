package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class GroupActiveProposalsRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 359; }
	@Nonnull
    public final String getName() { return "GroupActiveProposalsRequest"; }
	@Nonnull
    @Sequence(0)
	public GroupActiveProposalsRequest_bAgentData bagentdata=new GroupActiveProposalsRequest_bAgentData();
	@Nonnull
    @Sequence(1)
	public GroupActiveProposalsRequest_bGroupData bgroupdata=new GroupActiveProposalsRequest_bGroupData();
	@Nonnull
    @Sequence(2)
	public GroupActiveProposalsRequest_bTransactionData btransactiondata=new GroupActiveProposalsRequest_bTransactionData();
	public GroupActiveProposalsRequest(){}
	public GroupActiveProposalsRequest(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
