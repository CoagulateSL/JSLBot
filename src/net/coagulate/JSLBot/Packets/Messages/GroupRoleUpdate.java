package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class GroupRoleUpdate extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 378; }
	@Nonnull
    public final String getName() { return "GroupRoleUpdate"; }
	@Nonnull
    @Sequence(0)
	public GroupRoleUpdate_bAgentData bagentdata=new GroupRoleUpdate_bAgentData();
	@Sequence(1)
	public List<GroupRoleUpdate_bRoleData> broledata;
	public GroupRoleUpdate(){}
	public GroupRoleUpdate(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
