package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class AgentCachedTextureResponse extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 385; }
	@Nonnull
    public final String getName() { return "AgentCachedTextureResponse"; }
	@Nonnull
    @Sequence(0)
	public AgentCachedTextureResponse_bAgentData bagentdata=new AgentCachedTextureResponse_bAgentData();
	@Sequence(1)
	public List<AgentCachedTextureResponse_bWearableData> bwearabledata;
	public AgentCachedTextureResponse(){}
	public AgentCachedTextureResponse(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
