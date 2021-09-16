package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class GroupRoleMembersReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 374; }
	@Nonnull
    public final String getName() { return "GroupRoleMembersReply"; }
	@Nonnull
    @Sequence(0)
	public GroupRoleMembersReply_bAgentData bagentdata=new GroupRoleMembersReply_bAgentData();
	@Sequence(1)
	public List<GroupRoleMembersReply_bMemberData> bmemberdata;
	public GroupRoleMembersReply(){}
	public GroupRoleMembersReply(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
