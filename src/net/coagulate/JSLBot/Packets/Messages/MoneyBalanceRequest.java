package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class MoneyBalanceRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 313; }
	public final String getName() { return "MoneyBalanceRequest"; }
	@Sequence(0)
	public MoneyBalanceRequest_bAgentData bagentdata=new MoneyBalanceRequest_bAgentData();
	@Sequence(1)
	public MoneyBalanceRequest_bMoneyData bmoneydata=new MoneyBalanceRequest_bMoneyData();
}
