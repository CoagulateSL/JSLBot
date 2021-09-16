package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class GroupTitlesRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 375; }
	@Nonnull
    public final String getName() { return "GroupTitlesRequest"; }
	@Nonnull
    @Sequence(0)
	public GroupTitlesRequest_bAgentData bagentdata=new GroupTitlesRequest_bAgentData();
	public GroupTitlesRequest(){}
	public GroupTitlesRequest(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
