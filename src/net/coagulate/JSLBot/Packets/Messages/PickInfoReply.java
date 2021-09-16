package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class PickInfoReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 184; }
	@Nonnull
    public final String getName() { return "PickInfoReply"; }
	@Nonnull
    @Sequence(0)
	public PickInfoReply_bAgentData bagentdata=new PickInfoReply_bAgentData();
	@Nonnull
    @Sequence(1)
	public PickInfoReply_bData bdata=new PickInfoReply_bData();
	public PickInfoReply(){}
	public PickInfoReply(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
