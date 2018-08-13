package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AgentMovementComplete extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 250; }
	public final String getName() { return "AgentMovementComplete"; }
	@Sequence(0)
	public AgentMovementComplete_bAgentData bagentdata=new AgentMovementComplete_bAgentData();
	@Sequence(1)
	public AgentMovementComplete_bData bdata=new AgentMovementComplete_bData();
	@Sequence(2)
	public AgentMovementComplete_bSimData bsimdata=new AgentMovementComplete_bSimData();
	public AgentMovementComplete(){}
	public AgentMovementComplete(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
