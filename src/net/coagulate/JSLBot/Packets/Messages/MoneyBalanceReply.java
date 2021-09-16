package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class MoneyBalanceReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 314; }
	@Nonnull
    public final String getName() { return "MoneyBalanceReply"; }
	@Nonnull
    @Sequence(0)
	public MoneyBalanceReply_bMoneyData bmoneydata=new MoneyBalanceReply_bMoneyData();
	@Nonnull
    @Sequence(1)
	public MoneyBalanceReply_bTransactionInfo btransactioninfo=new MoneyBalanceReply_bTransactionInfo();
}
