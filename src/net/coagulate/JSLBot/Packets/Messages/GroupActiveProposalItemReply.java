package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GroupActiveProposalItemReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 360; }
	public final String getName() { return "GroupActiveProposalItemReply"; }
	@Sequence(0)
	public GroupActiveProposalItemReply_bAgentData bagentdata=new GroupActiveProposalItemReply_bAgentData();
	@Sequence(1)
	public GroupActiveProposalItemReply_bTransactionData btransactiondata=new GroupActiveProposalItemReply_bTransactionData();
	@Sequence(2)
	public List<GroupActiveProposalItemReply_bProposalData> bproposaldata;
	public GroupActiveProposalItemReply(){}
	public GroupActiveProposalItemReply(JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
