package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class GroupNoticeAdd extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 61; }
	@Nonnull
    public final String getName() { return "GroupNoticeAdd"; }
	@Nonnull
    @Sequence(0)
	public GroupNoticeAdd_bAgentData bagentdata=new GroupNoticeAdd_bAgentData();
	@Nonnull
    @Sequence(1)
	public GroupNoticeAdd_bMessageBlock bmessageblock=new GroupNoticeAdd_bMessageBlock();
	public GroupNoticeAdd(){}
	public GroupNoticeAdd(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
