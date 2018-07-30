package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AgentRequestSit extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 6; }
	public final String getName() { return "AgentRequestSit"; }
	@Sequence(0)
	public AgentRequestSit_bAgentData bagentdata=new AgentRequestSit_bAgentData();
	@Sequence(1)
	public AgentRequestSit_bTargetObject btargetobject=new AgentRequestSit_bTargetObject();
}
