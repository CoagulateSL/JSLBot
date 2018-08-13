package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class EventInfoRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 179; }
	public final String getName() { return "EventInfoRequest"; }
	@Sequence(0)
	public EventInfoRequest_bAgentData bagentdata=new EventInfoRequest_bAgentData();
	@Sequence(1)
	public EventInfoRequest_bEventData beventdata=new EventInfoRequest_bEventData();
	public EventInfoRequest(){}
	public EventInfoRequest(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
