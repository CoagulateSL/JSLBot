package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class AgentAlertMessage extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 135; }
	@Nonnull
    public final String getName() { return "AgentAlertMessage"; }
	@Nonnull
    @Sequence(0)
	public AgentAlertMessage_bAgentData bagentdata=new AgentAlertMessage_bAgentData();
	@Nonnull
    @Sequence(1)
	public AgentAlertMessage_bAlertData balertdata=new AgentAlertMessage_bAlertData();
	public AgentAlertMessage(){}
	public AgentAlertMessage(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
