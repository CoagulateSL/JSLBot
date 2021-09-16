package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class GroupProfileReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 352; }
	@Nonnull
    public final String getName() { return "GroupProfileReply"; }
	@Nonnull
    @Sequence(0)
	public GroupProfileReply_bAgentData bagentdata=new GroupProfileReply_bAgentData();
	@Nonnull
    @Sequence(1)
	public GroupProfileReply_bGroupData bgroupdata=new GroupProfileReply_bGroupData();
	public GroupProfileReply(){}
	public GroupProfileReply(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
