package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class DirPopularReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 53; }
	@Nonnull
    public final String getName() { return "DirPopularReply"; }
	@Nonnull
    @Sequence(0)
	public DirPopularReply_bAgentData bagentdata=new DirPopularReply_bAgentData();
	@Nonnull
    @Sequence(1)
	public DirPopularReply_bQueryData bquerydata=new DirPopularReply_bQueryData();
	@Sequence(2)
	public List<DirPopularReply_bQueryReplies> bqueryreplies;
	public DirPopularReply(){}
	public DirPopularReply(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
