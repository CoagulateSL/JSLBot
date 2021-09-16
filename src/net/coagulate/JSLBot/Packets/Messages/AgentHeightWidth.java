package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class AgentHeightWidth extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 83; }
	@Nonnull
    public final String getName() { return "AgentHeightWidth"; }
	@Nonnull
    @Sequence(0)
	public AgentHeightWidth_bAgentData bagentdata=new AgentHeightWidth_bAgentData();
	@Nonnull
    @Sequence(1)
	public AgentHeightWidth_bHeightWidthBlock bheightwidthblock=new AgentHeightWidth_bHeightWidthBlock();
	public AgentHeightWidth(){}
	public AgentHeightWidth(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
