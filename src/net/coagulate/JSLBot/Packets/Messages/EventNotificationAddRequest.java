package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class EventNotificationAddRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 181; }
	@Nonnull
    public final String getName() { return "EventNotificationAddRequest"; }
	@Nonnull
    @Sequence(0)
	public EventNotificationAddRequest_bAgentData bagentdata=new EventNotificationAddRequest_bAgentData();
	@Nonnull
    @Sequence(1)
	public EventNotificationAddRequest_bEventData beventdata=new EventNotificationAddRequest_bEventData();
	public EventNotificationAddRequest(){}
	public EventNotificationAddRequest(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
