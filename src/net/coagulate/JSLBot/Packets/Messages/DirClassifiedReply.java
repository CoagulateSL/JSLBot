package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class DirClassifiedReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 41; }
	@Nonnull
    public final String getName() { return "DirClassifiedReply"; }
	@Nonnull
    @Sequence(0)
	public DirClassifiedReply_bAgentData bagentdata=new DirClassifiedReply_bAgentData();
	@Nonnull
    @Sequence(1)
	public DirClassifiedReply_bQueryData bquerydata=new DirClassifiedReply_bQueryData();
	@Sequence(2)
	public List<DirClassifiedReply_bQueryReplies> bqueryreplies;
	@Sequence(3)
	public List<DirClassifiedReply_bStatusData> bstatusdata;
	public DirClassifiedReply(){}
	public DirClassifiedReply(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
