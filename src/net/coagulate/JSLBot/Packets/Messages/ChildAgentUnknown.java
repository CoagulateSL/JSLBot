package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ChildAgentUnknown extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 241; }
	@Nonnull
    public final String getName() { return "ChildAgentUnknown"; }
	@Nonnull
    @Sequence(0)
	public ChildAgentUnknown_bAgentData bagentdata=new ChildAgentUnknown_bAgentData();
	public ChildAgentUnknown(){}
	public ChildAgentUnknown(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
