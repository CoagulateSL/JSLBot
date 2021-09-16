package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class EventNotificationRemoveRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 182; }
	@Nonnull
    public final String getName() { return "EventNotificationRemoveRequest"; }
	@Nonnull
    @Sequence(0)
	public EventNotificationRemoveRequest_bAgentData bagentdata=new EventNotificationRemoveRequest_bAgentData();
	@Nonnull
    @Sequence(1)
	public EventNotificationRemoveRequest_bEventData beventdata=new EventNotificationRemoveRequest_bEventData();
	public EventNotificationRemoveRequest(){}
	public EventNotificationRemoveRequest(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
