package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AgentUpdate extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 4; }
	public final String getName() { return "AgentUpdate"; }
	@Sequence(0)
	public AgentUpdate_bAgentData bagentdata=new AgentUpdate_bAgentData();
	public AgentUpdate(){}
	public AgentUpdate(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
