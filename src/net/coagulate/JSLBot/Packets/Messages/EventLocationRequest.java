package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class EventLocationRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 307; }
	public final String getName() { return "EventLocationRequest"; }
	@Sequence(0)
	public EventLocationRequest_bQueryData bquerydata=new EventLocationRequest_bQueryData();
	@Sequence(1)
	public EventLocationRequest_bEventData beventdata=new EventLocationRequest_bEventData();
}
