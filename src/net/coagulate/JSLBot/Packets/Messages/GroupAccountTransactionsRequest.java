package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class GroupAccountTransactionsRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 357; }
	@Nonnull
    public final String getName() { return "GroupAccountTransactionsRequest"; }
	@Nonnull
    @Sequence(0)
	public GroupAccountTransactionsRequest_bAgentData bagentdata=new GroupAccountTransactionsRequest_bAgentData();
	@Nonnull
    @Sequence(1)
	public GroupAccountTransactionsRequest_bMoneyData bmoneydata=new GroupAccountTransactionsRequest_bMoneyData();
	public GroupAccountTransactionsRequest(){}
	public GroupAccountTransactionsRequest(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
