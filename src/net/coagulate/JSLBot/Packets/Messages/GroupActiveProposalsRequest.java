package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GroupActiveProposalsRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 359; }
	public final String getName() { return "GroupActiveProposalsRequest"; }
	@Sequence(0)
	public GroupActiveProposalsRequest_bAgentData bagentdata=new GroupActiveProposalsRequest_bAgentData();
	@Sequence(1)
	public GroupActiveProposalsRequest_bGroupData bgroupdata=new GroupActiveProposalsRequest_bGroupData();
	@Sequence(2)
	public GroupActiveProposalsRequest_bTransactionData btransactiondata=new GroupActiveProposalsRequest_bTransactionData();
}
