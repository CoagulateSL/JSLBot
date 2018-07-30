package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AgentDataUpdate extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 387; }
	public final String getName() { return "AgentDataUpdate"; }
	@Sequence(0)
	public AgentDataUpdate_bAgentData bagentdata=new AgentDataUpdate_bAgentData();
}
