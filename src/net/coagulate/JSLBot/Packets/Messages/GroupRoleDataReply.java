package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class GroupRoleDataReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 372; }
	@Nonnull
    public final String getName() { return "GroupRoleDataReply"; }
	@Nonnull
    @Sequence(0)
	public GroupRoleDataReply_bAgentData bagentdata=new GroupRoleDataReply_bAgentData();
	@Nonnull
    @Sequence(1)
	public GroupRoleDataReply_bGroupData bgroupdata=new GroupRoleDataReply_bGroupData();
	@Sequence(2)
	public List<GroupRoleDataReply_bRoleData> broledata;
	public GroupRoleDataReply(){}
	public GroupRoleDataReply(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
