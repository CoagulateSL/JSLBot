package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class GroupNoticesListReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 59; }
	@Nonnull
    public final String getName() { return "GroupNoticesListReply"; }
	@Nonnull
    @Sequence(0)
	public GroupNoticesListReply_bAgentData bagentdata=new GroupNoticesListReply_bAgentData();
	@Sequence(1)
	public List<GroupNoticesListReply_bData> bdata;
	public GroupNoticesListReply(){}
	public GroupNoticesListReply(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
