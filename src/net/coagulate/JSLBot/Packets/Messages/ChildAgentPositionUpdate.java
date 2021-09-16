package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ChildAgentPositionUpdate extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 27; }
	@Nonnull
    public final String getName() { return "ChildAgentPositionUpdate"; }
	@Nonnull
    @Sequence(0)
	public ChildAgentPositionUpdate_bAgentData bagentdata=new ChildAgentPositionUpdate_bAgentData();
	public ChildAgentPositionUpdate(){}
	public ChildAgentPositionUpdate(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
