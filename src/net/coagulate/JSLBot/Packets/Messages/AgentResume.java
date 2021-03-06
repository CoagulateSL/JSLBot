package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AgentResume extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 79; }
	public final String getName() { return "AgentResume"; }
	@Sequence(0)
	public AgentResume_bAgentData bagentdata=new AgentResume_bAgentData();
	public AgentResume(){}
	public AgentResume(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
