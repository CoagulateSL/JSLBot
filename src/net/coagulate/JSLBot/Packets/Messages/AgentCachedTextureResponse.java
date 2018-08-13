package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AgentCachedTextureResponse extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 385; }
	public final String getName() { return "AgentCachedTextureResponse"; }
	@Sequence(0)
	public AgentCachedTextureResponse_bAgentData bagentdata=new AgentCachedTextureResponse_bAgentData();
	@Sequence(1)
	public List<AgentCachedTextureResponse_bWearableData> bwearabledata;
	public AgentCachedTextureResponse(){}
	public AgentCachedTextureResponse(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
