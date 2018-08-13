package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GroupAccountDetailsRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 355; }
	public final String getName() { return "GroupAccountDetailsRequest"; }
	@Sequence(0)
	public GroupAccountDetailsRequest_bAgentData bagentdata=new GroupAccountDetailsRequest_bAgentData();
	@Sequence(1)
	public GroupAccountDetailsRequest_bMoneyData bmoneydata=new GroupAccountDetailsRequest_bMoneyData();
	public GroupAccountDetailsRequest(){}
	public GroupAccountDetailsRequest(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
