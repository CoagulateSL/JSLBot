package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GroupAccountDetailsReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 356; }
	public final String getName() { return "GroupAccountDetailsReply"; }
	@Sequence(0)
	public GroupAccountDetailsReply_bAgentData bagentdata=new GroupAccountDetailsReply_bAgentData();
	@Sequence(1)
	public GroupAccountDetailsReply_bMoneyData bmoneydata=new GroupAccountDetailsReply_bMoneyData();
	@Sequence(2)
	public List<GroupAccountDetailsReply_bHistoryData> bhistorydata;
}
