package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class EventLocationReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 308; }
	public final String getName() { return "EventLocationReply"; }
	@Sequence(0)
	public EventLocationReply_bQueryData bquerydata=new EventLocationReply_bQueryData();
	@Sequence(1)
	public EventLocationReply_bEventData beventdata=new EventLocationReply_bEventData();
}
