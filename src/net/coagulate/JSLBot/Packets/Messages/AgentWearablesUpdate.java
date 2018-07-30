package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AgentWearablesUpdate extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 382; }
	public final String getName() { return "AgentWearablesUpdate"; }
	@Sequence(0)
	public AgentWearablesUpdate_bAgentData bagentdata=new AgentWearablesUpdate_bAgentData();
	@Sequence(1)
	public List<AgentWearablesUpdate_bWearableData> bwearabledata;
}
