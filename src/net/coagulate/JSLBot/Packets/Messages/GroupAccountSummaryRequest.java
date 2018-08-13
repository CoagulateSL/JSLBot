package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GroupAccountSummaryRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 353; }
	public final String getName() { return "GroupAccountSummaryRequest"; }
	@Sequence(0)
	public GroupAccountSummaryRequest_bAgentData bagentdata=new GroupAccountSummaryRequest_bAgentData();
	@Sequence(1)
	public GroupAccountSummaryRequest_bMoneyData bmoneydata=new GroupAccountSummaryRequest_bMoneyData();
	public GroupAccountSummaryRequest(){}
	public GroupAccountSummaryRequest(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
