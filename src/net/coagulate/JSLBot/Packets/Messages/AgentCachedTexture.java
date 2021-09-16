package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class AgentCachedTexture extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 384; }
	@Nonnull
    public final String getName() { return "AgentCachedTexture"; }
	@Nonnull
    @Sequence(0)
	public AgentCachedTexture_bAgentData bagentdata=new AgentCachedTexture_bAgentData();
	@Sequence(1)
	public List<AgentCachedTexture_bWearableData> bwearabledata;
	public AgentCachedTexture(){}
	public AgentCachedTexture(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
