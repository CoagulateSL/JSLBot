package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class AgentThrottle extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 81; }
	@Nonnull
    public final String getName() { return "AgentThrottle"; }
	@Nonnull
    @Sequence(0)
	public AgentThrottle_bAgentData bagentdata=new AgentThrottle_bAgentData();
	@Nonnull
    @Sequence(1)
	public AgentThrottle_bThrottle bthrottle=new AgentThrottle_bThrottle();
	public AgentThrottle(){}
	public AgentThrottle(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
