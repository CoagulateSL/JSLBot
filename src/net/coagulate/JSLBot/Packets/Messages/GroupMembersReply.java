package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class GroupMembersReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 367; }
	@Nonnull
    public final String getName() { return "GroupMembersReply"; }
	@Nonnull
    @Sequence(0)
	public GroupMembersReply_bAgentData bagentdata=new GroupMembersReply_bAgentData();
	@Nonnull
    @Sequence(1)
	public GroupMembersReply_bGroupData bgroupdata=new GroupMembersReply_bGroupData();
	@Sequence(2)
	public List<GroupMembersReply_bMemberData> bmemberdata;
	public GroupMembersReply(){}
	public GroupMembersReply(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
