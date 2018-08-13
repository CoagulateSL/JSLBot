package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GroupVoteHistoryRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 361; }
	public final String getName() { return "GroupVoteHistoryRequest"; }
	@Sequence(0)
	public GroupVoteHistoryRequest_bAgentData bagentdata=new GroupVoteHistoryRequest_bAgentData();
	@Sequence(1)
	public GroupVoteHistoryRequest_bGroupData bgroupdata=new GroupVoteHistoryRequest_bGroupData();
	@Sequence(2)
	public GroupVoteHistoryRequest_bTransactionData btransactiondata=new GroupVoteHistoryRequest_bTransactionData();
	public GroupVoteHistoryRequest(){}
	public GroupVoteHistoryRequest(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
