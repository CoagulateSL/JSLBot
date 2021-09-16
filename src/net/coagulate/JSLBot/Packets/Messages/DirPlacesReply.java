package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class DirPlacesReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 35; }
	@Nonnull
    public final String getName() { return "DirPlacesReply"; }
	@Nonnull
    @Sequence(0)
	public DirPlacesReply_bAgentData bagentdata=new DirPlacesReply_bAgentData();
	@Sequence(1)
	public List<DirPlacesReply_bQueryData> bquerydata;
	@Sequence(2)
	public List<DirPlacesReply_bQueryReplies> bqueryreplies;
	@Sequence(3)
	public List<DirPlacesReply_bStatusData> bstatusdata;
	public DirPlacesReply(){}
	public DirPlacesReply(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
