package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class AgentResume extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 79; }
	@Nonnull
    public final String getName() { return "AgentResume"; }
	@Nonnull
    @Sequence(0)
	public AgentResume_bAgentData bagentdata=new AgentResume_bAgentData();
	public AgentResume(){}
	public AgentResume(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
