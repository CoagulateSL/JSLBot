package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class DirGroupsReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 38; }
	@Nonnull
    public final String getName() { return "DirGroupsReply"; }
	@Nonnull
    @Sequence(0)
	public DirGroupsReply_bAgentData bagentdata=new DirGroupsReply_bAgentData();
	@Nonnull
    @Sequence(1)
	public DirGroupsReply_bQueryData bquerydata=new DirGroupsReply_bQueryData();
	@Sequence(2)
	public List<DirGroupsReply_bQueryReplies> bqueryreplies;
	public DirGroupsReply(){}
	public DirGroupsReply(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
