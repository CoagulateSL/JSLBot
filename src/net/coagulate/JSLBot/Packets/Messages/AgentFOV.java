package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AgentFOV extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 82; }
	public final String getName() { return "AgentFOV"; }
	@Sequence(0)
	public AgentFOV_bAgentData bagentdata=new AgentFOV_bAgentData();
	@Sequence(1)
	public AgentFOV_bFOVBlock bfovblock=new AgentFOV_bFOVBlock();
}
