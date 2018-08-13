package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class EventNotificationAddRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 181; }
	public final String getName() { return "EventNotificationAddRequest"; }
	@Sequence(0)
	public EventNotificationAddRequest_bAgentData bagentdata=new EventNotificationAddRequest_bAgentData();
	@Sequence(1)
	public EventNotificationAddRequest_bEventData beventdata=new EventNotificationAddRequest_bEventData();
	public EventNotificationAddRequest(){}
	public EventNotificationAddRequest(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
