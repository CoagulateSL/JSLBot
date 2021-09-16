package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class GroupTitlesReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 376; }
	@Nonnull
    public final String getName() { return "GroupTitlesReply"; }
	@Nonnull
    @Sequence(0)
	public GroupTitlesReply_bAgentData bagentdata=new GroupTitlesReply_bAgentData();
	@Sequence(1)
	public List<GroupTitlesReply_bGroupData> bgroupdata;
	public GroupTitlesReply(){}
	public GroupTitlesReply(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
