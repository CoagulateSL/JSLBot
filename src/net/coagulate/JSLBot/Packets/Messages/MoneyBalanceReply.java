package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class MoneyBalanceReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 314; }
	public final String getName() { return "MoneyBalanceReply"; }
	@Sequence(0)
	public MoneyBalanceReply_bMoneyData bmoneydata=new MoneyBalanceReply_bMoneyData();
	@Sequence(1)
	public MoneyBalanceReply_bTransactionInfo btransactioninfo=new MoneyBalanceReply_bTransactionInfo();
}
