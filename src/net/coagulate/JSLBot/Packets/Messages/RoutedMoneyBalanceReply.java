package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class RoutedMoneyBalanceReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 315; }
	@Nonnull
    public final String getName() { return "RoutedMoneyBalanceReply"; }
	@Nonnull
    @Sequence(0)
	public RoutedMoneyBalanceReply_bTargetBlock btargetblock=new RoutedMoneyBalanceReply_bTargetBlock();
	@Nonnull
    @Sequence(1)
	public RoutedMoneyBalanceReply_bMoneyData bmoneydata=new RoutedMoneyBalanceReply_bMoneyData();
	@Nonnull
    @Sequence(2)
	public RoutedMoneyBalanceReply_bTransactionInfo btransactioninfo=new RoutedMoneyBalanceReply_bTransactionInfo();
}
