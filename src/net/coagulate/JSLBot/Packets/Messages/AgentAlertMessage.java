package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AgentAlertMessage extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 135; }
	public final String getName() { return "AgentAlertMessage"; }
	@Sequence(0)
	public AgentAlertMessage_bAgentData bagentdata=new AgentAlertMessage_bAgentData();
	@Sequence(1)
	public AgentAlertMessage_bAlertData balertdata=new AgentAlertMessage_bAlertData();
}
