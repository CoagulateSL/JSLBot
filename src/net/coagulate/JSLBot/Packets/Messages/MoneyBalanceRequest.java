package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class MoneyBalanceRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 313; }
	@Nonnull
    public final String getName() { return "MoneyBalanceRequest"; }
	@Nonnull
    @Sequence(0)
	public MoneyBalanceRequest_bAgentData bagentdata=new MoneyBalanceRequest_bAgentData();
	@Nonnull
    @Sequence(1)
	public MoneyBalanceRequest_bMoneyData bmoneydata=new MoneyBalanceRequest_bMoneyData();
	public MoneyBalanceRequest(){}
	public MoneyBalanceRequest(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
