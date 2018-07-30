package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class EventNotificationRemoveRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 182; }
	public final String getName() { return "EventNotificationRemoveRequest"; }
	@Sequence(0)
	public EventNotificationRemoveRequest_bAgentData bagentdata=new EventNotificationRemoveRequest_bAgentData();
	@Sequence(1)
	public EventNotificationRemoveRequest_bEventData beventdata=new EventNotificationRemoveRequest_bEventData();
}
