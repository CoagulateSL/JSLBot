package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class GroupAccountDetailsReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 356; }
	@Nonnull
    public final String getName() { return "GroupAccountDetailsReply"; }
	@Nonnull
    @Sequence(0)
	public GroupAccountDetailsReply_bAgentData bagentdata=new GroupAccountDetailsReply_bAgentData();
	@Nonnull
    @Sequence(1)
	public GroupAccountDetailsReply_bMoneyData bmoneydata=new GroupAccountDetailsReply_bMoneyData();
	@Sequence(2)
	public List<GroupAccountDetailsReply_bHistoryData> bhistorydata;
	public GroupAccountDetailsReply(){}
	public GroupAccountDetailsReply(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
