package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class AgentSetAppearance extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 84; }
	@Nonnull
    public final String getName() { return "AgentSetAppearance"; }
	@Nonnull
    @Sequence(0)
	public AgentSetAppearance_bAgentData bagentdata=new AgentSetAppearance_bAgentData();
	@Sequence(1)
	public List<AgentSetAppearance_bWearableData> bwearabledata;
	@Nonnull
    @Sequence(2)
	public AgentSetAppearance_bObjectData bobjectdata=new AgentSetAppearance_bObjectData();
	@Sequence(3)
	public List<AgentSetAppearance_bVisualParam> bvisualparam;
	public AgentSetAppearance(){}
	public AgentSetAppearance(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
