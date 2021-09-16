package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class GroupMembersRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 366; }
	@Nonnull
    public final String getName() { return "GroupMembersRequest"; }
	@Nonnull
    @Sequence(0)
	public GroupMembersRequest_bAgentData bagentdata=new GroupMembersRequest_bAgentData();
	@Nonnull
    @Sequence(1)
	public GroupMembersRequest_bGroupData bgroupdata=new GroupMembersRequest_bGroupData();
	public GroupMembersRequest(){}
	public GroupMembersRequest(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
