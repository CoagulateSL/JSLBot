package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class GroupRoleChanges extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 342; }
	@Nonnull
    public final String getName() { return "GroupRoleChanges"; }
	@Nonnull
    @Sequence(0)
	public GroupRoleChanges_bAgentData bagentdata=new GroupRoleChanges_bAgentData();
	@Sequence(1)
	public List<GroupRoleChanges_bRoleChange> brolechange;
	public GroupRoleChanges(){}
	public GroupRoleChanges(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
