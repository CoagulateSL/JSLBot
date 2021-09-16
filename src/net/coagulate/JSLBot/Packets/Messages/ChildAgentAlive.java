package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ChildAgentAlive extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 26; }
	@Nonnull
    public final String getName() { return "ChildAgentAlive"; }
	@Nonnull
    @Sequence(0)
	public ChildAgentAlive_bAgentData bagentdata=new ChildAgentAlive_bAgentData();
	public ChildAgentAlive(){}
	public ChildAgentAlive(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
