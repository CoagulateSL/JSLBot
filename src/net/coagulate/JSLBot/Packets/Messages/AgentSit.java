package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AgentSit extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 7; }
	public final String getName() { return "AgentSit"; }
	@Sequence(0)
	public AgentSit_bAgentData bagentdata=new AgentSit_bAgentData();
	public AgentSit(){}
	public AgentSit(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
