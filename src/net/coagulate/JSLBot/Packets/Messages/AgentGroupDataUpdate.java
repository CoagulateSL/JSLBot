package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AgentGroupDataUpdate extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 389; }
	public final String getName() { return "AgentGroupDataUpdate"; }
	@Sequence(0)
	public AgentGroupDataUpdate_bAgentData bagentdata=new AgentGroupDataUpdate_bAgentData();
	@Sequence(1)
	public List<AgentGroupDataUpdate_bGroupData> bgroupdata;
	public AgentGroupDataUpdate(){}
	public AgentGroupDataUpdate(JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
