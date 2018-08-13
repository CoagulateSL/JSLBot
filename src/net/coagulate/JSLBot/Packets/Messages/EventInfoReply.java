package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class EventInfoReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 180; }
	public final String getName() { return "EventInfoReply"; }
	@Sequence(0)
	public EventInfoReply_bAgentData bagentdata=new EventInfoReply_bAgentData();
	@Sequence(1)
	public EventInfoReply_bEventData beventdata=new EventInfoReply_bEventData();
	public EventInfoReply(){}
	public EventInfoReply(JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
