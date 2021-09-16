package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class AgentDataUpdateRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 386; }
	@Nonnull
    public final String getName() { return "AgentDataUpdateRequest"; }
	@Nonnull
    @Sequence(0)
	public AgentDataUpdateRequest_bAgentData bagentdata=new AgentDataUpdateRequest_bAgentData();
	public AgentDataUpdateRequest(){}
	public AgentDataUpdateRequest(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
