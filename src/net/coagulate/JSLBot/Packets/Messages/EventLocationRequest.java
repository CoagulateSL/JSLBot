package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class EventLocationRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 307; }
	@Nonnull
    public final String getName() { return "EventLocationRequest"; }
	@Nonnull
    @Sequence(0)
	public EventLocationRequest_bQueryData bquerydata=new EventLocationRequest_bQueryData();
	@Nonnull
    @Sequence(1)
	public EventLocationRequest_bEventData beventdata=new EventLocationRequest_bEventData();
}
