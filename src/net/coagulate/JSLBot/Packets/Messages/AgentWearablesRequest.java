package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class AgentWearablesRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 381; }
	@Nonnull
    public final String getName() { return "AgentWearablesRequest"; }
	@Nonnull
    @Sequence(0)
	public AgentWearablesRequest_bAgentData bagentdata=new AgentWearablesRequest_bAgentData();
	public AgentWearablesRequest(){}
	public AgentWearablesRequest(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
