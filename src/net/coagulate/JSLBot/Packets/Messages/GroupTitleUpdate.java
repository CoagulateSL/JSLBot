package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class GroupTitleUpdate extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 377; }
	@Nonnull
    public final String getName() { return "GroupTitleUpdate"; }
	@Nonnull
    @Sequence(0)
	public GroupTitleUpdate_bAgentData bagentdata=new GroupTitleUpdate_bAgentData();
	public GroupTitleUpdate(){}
	public GroupTitleUpdate(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
