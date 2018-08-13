package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class MoneyTransferBackend extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 312; }
	public final String getName() { return "MoneyTransferBackend"; }
	@Sequence(0)
	public MoneyTransferBackend_bMoneyData bmoneydata=new MoneyTransferBackend_bMoneyData();
}
