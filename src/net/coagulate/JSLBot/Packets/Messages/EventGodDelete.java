package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class EventGodDelete extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 183; }
	public final String getName() { return "EventGodDelete"; }
	@Sequence(0)
	public EventGodDelete_bAgentData bagentdata=new EventGodDelete_bAgentData();
	@Sequence(1)
	public EventGodDelete_bEventData beventdata=new EventGodDelete_bEventData();
	@Sequence(2)
	public EventGodDelete_bQueryData bquerydata=new EventGodDelete_bQueryData();
}
