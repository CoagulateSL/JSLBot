package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class EventLocationReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 308; }
	@Nonnull
    public final String getName() { return "EventLocationReply"; }
	@Nonnull
    @Sequence(0)
	public EventLocationReply_bQueryData bquerydata=new EventLocationReply_bQueryData();
	@Nonnull
    @Sequence(1)
	public EventLocationReply_bEventData beventdata=new EventLocationReply_bEventData();
}
