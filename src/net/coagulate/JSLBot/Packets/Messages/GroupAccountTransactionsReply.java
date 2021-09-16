package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class GroupAccountTransactionsReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 358; }
	@Nonnull
    public final String getName() { return "GroupAccountTransactionsReply"; }
	@Nonnull
    @Sequence(0)
	public GroupAccountTransactionsReply_bAgentData bagentdata=new GroupAccountTransactionsReply_bAgentData();
	@Nonnull
    @Sequence(1)
	public GroupAccountTransactionsReply_bMoneyData bmoneydata=new GroupAccountTransactionsReply_bMoneyData();
	@Sequence(2)
	public List<GroupAccountTransactionsReply_bHistoryData> bhistorydata;
	public GroupAccountTransactionsReply(){}
	public GroupAccountTransactionsReply(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
