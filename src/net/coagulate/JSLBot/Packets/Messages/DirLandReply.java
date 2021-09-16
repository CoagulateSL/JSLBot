package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class DirLandReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 50; }
	@Nonnull
    public final String getName() { return "DirLandReply"; }
	@Nonnull
    @Sequence(0)
	public DirLandReply_bAgentData bagentdata=new DirLandReply_bAgentData();
	@Nonnull
    @Sequence(1)
	public DirLandReply_bQueryData bquerydata=new DirLandReply_bQueryData();
	@Sequence(2)
	public List<DirLandReply_bQueryReplies> bqueryreplies;
	public DirLandReply(){}
	public DirLandReply(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
