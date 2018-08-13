package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class LogFailedMoneyTransaction extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 20; }
	public final String getName() { return "LogFailedMoneyTransaction"; }
	@Sequence(0)
	public LogFailedMoneyTransaction_bTransactionData btransactiondata=new LogFailedMoneyTransaction_bTransactionData();
}
