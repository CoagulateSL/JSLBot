package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RoutedMoneyBalanceReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 315; }
	public final String getName() { return "RoutedMoneyBalanceReply"; }
	@Sequence(0)
	public RoutedMoneyBalanceReply_bTargetBlock btargetblock=new RoutedMoneyBalanceReply_bTargetBlock();
	@Sequence(1)
	public RoutedMoneyBalanceReply_bMoneyData bmoneydata=new RoutedMoneyBalanceReply_bMoneyData();
	@Sequence(2)
	public RoutedMoneyBalanceReply_bTransactionInfo btransactioninfo=new RoutedMoneyBalanceReply_bTransactionInfo();
}
