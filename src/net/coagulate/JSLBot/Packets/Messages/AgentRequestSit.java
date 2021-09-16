package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class AgentRequestSit extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 6; }
	@Nonnull
    public final String getName() { return "AgentRequestSit"; }
	@Nonnull
    @Sequence(0)
	public AgentRequestSit_bAgentData bagentdata=new AgentRequestSit_bAgentData();
	@Nonnull
    @Sequence(1)
	public AgentRequestSit_bTargetObject btargetobject=new AgentRequestSit_bTargetObject();
	public AgentRequestSit(){}
	public AgentRequestSit(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
