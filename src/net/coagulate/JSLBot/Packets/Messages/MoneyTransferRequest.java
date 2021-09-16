package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class MoneyTransferRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 311; }
	@Nonnull
    public final String getName() { return "MoneyTransferRequest"; }
	@Nonnull
    @Sequence(0)
	public MoneyTransferRequest_bAgentData bagentdata=new MoneyTransferRequest_bAgentData();
	@Nonnull
    @Sequence(1)
	public MoneyTransferRequest_bMoneyData bmoneydata=new MoneyTransferRequest_bMoneyData();
	public MoneyTransferRequest(){}
	public MoneyTransferRequest(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
