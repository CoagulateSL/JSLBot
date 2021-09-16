package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class GroupRoleDataRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 371; }
	@Nonnull
    public final String getName() { return "GroupRoleDataRequest"; }
	@Nonnull
    @Sequence(0)
	public GroupRoleDataRequest_bAgentData bagentdata=new GroupRoleDataRequest_bAgentData();
	@Nonnull
    @Sequence(1)
	public GroupRoleDataRequest_bGroupData bgroupdata=new GroupRoleDataRequest_bGroupData();
	public GroupRoleDataRequest(){}
	public GroupRoleDataRequest(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
