package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class GroupNoticesListRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 58; }
	@Nonnull
    public final String getName() { return "GroupNoticesListRequest"; }
	@Nonnull
    @Sequence(0)
	public GroupNoticesListRequest_bAgentData bagentdata=new GroupNoticesListRequest_bAgentData();
	@Nonnull
    @Sequence(1)
	public GroupNoticesListRequest_bData bdata=new GroupNoticesListRequest_bData();
	public GroupNoticesListRequest(){}
	public GroupNoticesListRequest(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
