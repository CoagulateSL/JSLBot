package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GroupAccountTransactionsRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 357; }
	public final String getName() { return "GroupAccountTransactionsRequest"; }
	@Sequence(0)
	public GroupAccountTransactionsRequest_bAgentData bagentdata=new GroupAccountTransactionsRequest_bAgentData();
	@Sequence(1)
	public GroupAccountTransactionsRequest_bMoneyData bmoneydata=new GroupAccountTransactionsRequest_bMoneyData();
	public GroupAccountTransactionsRequest(){}
	public GroupAccountTransactionsRequest(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
