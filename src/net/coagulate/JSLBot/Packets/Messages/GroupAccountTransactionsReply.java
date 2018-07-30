package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GroupAccountTransactionsReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 358; }
	public final String getName() { return "GroupAccountTransactionsReply"; }
	@Sequence(0)
	public GroupAccountTransactionsReply_bAgentData bagentdata=new GroupAccountTransactionsReply_bAgentData();
	@Sequence(1)
	public GroupAccountTransactionsReply_bMoneyData bmoneydata=new GroupAccountTransactionsReply_bMoneyData();
	@Sequence(2)
	public List<GroupAccountTransactionsReply_bHistoryData> bhistorydata;
}
