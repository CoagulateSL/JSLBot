package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class AvatarPicksReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 178; }
	@Nonnull
    public final String getName() { return "AvatarPicksReply"; }
	@Nonnull
    @Sequence(0)
	public AvatarPicksReply_bAgentData bagentdata=new AvatarPicksReply_bAgentData();
	@Sequence(1)
	public List<AvatarPicksReply_bData> bdata;
	public AvatarPicksReply(){}
	public AvatarPicksReply(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
