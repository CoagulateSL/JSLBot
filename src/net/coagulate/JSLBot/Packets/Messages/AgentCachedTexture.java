package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AgentCachedTexture extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 384; }
	public final String getName() { return "AgentCachedTexture"; }
	@Sequence(0)
	public AgentCachedTexture_bAgentData bagentdata=new AgentCachedTexture_bAgentData();
	@Sequence(1)
	public List<AgentCachedTexture_bWearableData> bwearabledata;
	public AgentCachedTexture(){}
	public AgentCachedTexture(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
