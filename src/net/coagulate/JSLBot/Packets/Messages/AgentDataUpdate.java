package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class AgentDataUpdate extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 387; }
	@Nonnull
    public final String getName() { return "AgentDataUpdate"; }
	@Nonnull
    @Sequence(0)
	public AgentDataUpdate_bAgentData bagentdata=new AgentDataUpdate_bAgentData();
	public AgentDataUpdate(){}
	public AgentDataUpdate(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
