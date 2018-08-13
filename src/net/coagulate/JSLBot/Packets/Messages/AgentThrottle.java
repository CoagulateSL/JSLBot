package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AgentThrottle extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 81; }
	public final String getName() { return "AgentThrottle"; }
	@Sequence(0)
	public AgentThrottle_bAgentData bagentdata=new AgentThrottle_bAgentData();
	@Sequence(1)
	public AgentThrottle_bThrottle bthrottle=new AgentThrottle_bThrottle();
	public AgentThrottle(){}
	public AgentThrottle(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
