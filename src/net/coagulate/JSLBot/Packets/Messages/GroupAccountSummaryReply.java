package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GroupAccountSummaryReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 354; }
	public final String getName() { return "GroupAccountSummaryReply"; }
	@Sequence(0)
	public GroupAccountSummaryReply_bAgentData bagentdata=new GroupAccountSummaryReply_bAgentData();
	@Sequence(1)
	public GroupAccountSummaryReply_bMoneyData bmoneydata=new GroupAccountSummaryReply_bMoneyData();
	public GroupAccountSummaryReply(){}
	public GroupAccountSummaryReply(JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
