package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class AgentSit extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 7; }
	@Nonnull
    public final String getName() { return "AgentSit"; }
	@Nonnull
    @Sequence(0)
	public AgentSit_bAgentData bagentdata=new AgentSit_bAgentData();
	public AgentSit(){}
	public AgentSit(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
