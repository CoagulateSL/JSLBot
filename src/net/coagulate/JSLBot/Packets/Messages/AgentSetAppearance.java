package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AgentSetAppearance extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 84; }
	public final String getName() { return "AgentSetAppearance"; }
	@Sequence(0)
	public AgentSetAppearance_bAgentData bagentdata=new AgentSetAppearance_bAgentData();
	@Sequence(1)
	public List<AgentSetAppearance_bWearableData> bwearabledata;
	@Sequence(2)
	public AgentSetAppearance_bObjectData bobjectdata=new AgentSetAppearance_bObjectData();
	@Sequence(3)
	public List<AgentSetAppearance_bVisualParam> bvisualparam;
}
