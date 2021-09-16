package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ChildAgentDying extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 240; }
	@Nonnull
    public final String getName() { return "ChildAgentDying"; }
	@Nonnull
    @Sequence(0)
	public ChildAgentDying_bAgentData bagentdata=new ChildAgentDying_bAgentData();
	public ChildAgentDying(){}
	public ChildAgentDying(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
