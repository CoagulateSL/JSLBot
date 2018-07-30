package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AgentDropGroup extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 390; }
	public final String getName() { return "AgentDropGroup"; }
	@Sequence(0)
	public AgentDropGroup_bAgentData bagentdata=new AgentDropGroup_bAgentData();
}
