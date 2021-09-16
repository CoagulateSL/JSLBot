package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class EventInfoRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 179; }
	@Nonnull
    public final String getName() { return "EventInfoRequest"; }
	@Nonnull
    @Sequence(0)
	public EventInfoRequest_bAgentData bagentdata=new EventInfoRequest_bAgentData();
	@Nonnull
    @Sequence(1)
	public EventInfoRequest_bEventData beventdata=new EventInfoRequest_bEventData();
	public EventInfoRequest(){}
	public EventInfoRequest(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
