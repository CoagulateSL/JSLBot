package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class AgentWearablesUpdate extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 382; }
	@Nonnull
    public final String getName() { return "AgentWearablesUpdate"; }
	@Nonnull
    @Sequence(0)
	public AgentWearablesUpdate_bAgentData bagentdata=new AgentWearablesUpdate_bAgentData();
	@Sequence(1)
	public List<AgentWearablesUpdate_bWearableData> bwearabledata;
	public AgentWearablesUpdate(){}
	public AgentWearablesUpdate(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
