package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AgentWearablesRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 381; }
	public final String getName() { return "AgentWearablesRequest"; }
	@Sequence(0)
	public AgentWearablesRequest_bAgentData bagentdata=new AgentWearablesRequest_bAgentData();
}
