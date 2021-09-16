package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class AgentMovementComplete extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 250; }
	@Nonnull
    public final String getName() { return "AgentMovementComplete"; }
	@Nonnull
    @Sequence(0)
	public AgentMovementComplete_bAgentData bagentdata=new AgentMovementComplete_bAgentData();
	@Nonnull
    @Sequence(1)
	public AgentMovementComplete_bData bdata=new AgentMovementComplete_bData();
	@Nonnull
    @Sequence(2)
	public AgentMovementComplete_bSimData bsimdata=new AgentMovementComplete_bSimData();
	public AgentMovementComplete(){}
	public AgentMovementComplete(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
