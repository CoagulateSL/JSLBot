package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AgentDataUpdateRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 386; }
	public final String getName() { return "AgentDataUpdateRequest"; }
	@Sequence(0)
	public AgentDataUpdateRequest_bAgentData bagentdata=new AgentDataUpdateRequest_bAgentData();
}
