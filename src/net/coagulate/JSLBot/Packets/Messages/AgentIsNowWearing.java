package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AgentIsNowWearing extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 383; }
	public final String getName() { return "AgentIsNowWearing"; }
	@Sequence(0)
	public AgentIsNowWearing_bAgentData bagentdata=new AgentIsNowWearing_bAgentData();
	@Sequence(1)
	public List<AgentIsNowWearing_bWearableData> bwearabledata;
	public AgentIsNowWearing(){}
	public AgentIsNowWearing(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
