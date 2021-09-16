package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class DirEventsReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 37; }
	@Nonnull
    public final String getName() { return "DirEventsReply"; }
	@Nonnull
    @Sequence(0)
	public DirEventsReply_bAgentData bagentdata=new DirEventsReply_bAgentData();
	@Nonnull
    @Sequence(1)
	public DirEventsReply_bQueryData bquerydata=new DirEventsReply_bQueryData();
	@Sequence(2)
	public List<DirEventsReply_bQueryReplies> bqueryreplies;
	@Sequence(3)
	public List<DirEventsReply_bStatusData> bstatusdata;
	public DirEventsReply(){}
	public DirEventsReply(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
