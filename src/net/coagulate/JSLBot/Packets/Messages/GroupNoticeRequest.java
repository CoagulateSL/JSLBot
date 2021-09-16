package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class GroupNoticeRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 60; }
	@Nonnull
    public final String getName() { return "GroupNoticeRequest"; }
	@Nonnull
    @Sequence(0)
	public GroupNoticeRequest_bAgentData bagentdata=new GroupNoticeRequest_bAgentData();
	@Nonnull
    @Sequence(1)
	public GroupNoticeRequest_bData bdata=new GroupNoticeRequest_bData();
	public GroupNoticeRequest(){}
	public GroupNoticeRequest(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
