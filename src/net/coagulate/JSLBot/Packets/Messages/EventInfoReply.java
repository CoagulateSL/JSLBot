package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class EventInfoReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 180; }
	@Nonnull
    public final String getName() { return "EventInfoReply"; }
	@Nonnull
    @Sequence(0)
	public EventInfoReply_bAgentData bagentdata=new EventInfoReply_bAgentData();
	@Nonnull
    @Sequence(1)
	public EventInfoReply_bEventData beventdata=new EventInfoReply_bEventData();
	public EventInfoReply(){}
	public EventInfoReply(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
