package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class AgentIsNowWearing extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 383; }
	@Nonnull
    public final String getName() { return "AgentIsNowWearing"; }
	@Nonnull
    @Sequence(0)
	public AgentIsNowWearing_bAgentData bagentdata=new AgentIsNowWearing_bAgentData();
	@Sequence(1)
	public List<AgentIsNowWearing_bWearableData> bwearabledata;
	public AgentIsNowWearing(){}
	public AgentIsNowWearing(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
