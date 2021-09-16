package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class MoneyTransferBackend extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 312; }
	@Nonnull
    public final String getName() { return "MoneyTransferBackend"; }
	@Nonnull
    @Sequence(0)
	public MoneyTransferBackend_bMoneyData bmoneydata=new MoneyTransferBackend_bMoneyData();
}
