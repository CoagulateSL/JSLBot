package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AgentPause extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 78; }
	public final String getName() { return "AgentPause"; }
	@Sequence(0)
	public AgentPause_bAgentData bagentdata=new AgentPause_bAgentData();
	public AgentPause(){}
	public AgentPause(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
