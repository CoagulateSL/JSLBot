package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class MoneyTransferRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 311; }
	public final String getName() { return "MoneyTransferRequest"; }
	@Sequence(0)
	public MoneyTransferRequest_bAgentData bagentdata=new MoneyTransferRequest_bAgentData();
	@Sequence(1)
	public MoneyTransferRequest_bMoneyData bmoneydata=new MoneyTransferRequest_bMoneyData();
}
