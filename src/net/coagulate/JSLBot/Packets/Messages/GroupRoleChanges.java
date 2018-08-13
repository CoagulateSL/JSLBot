package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GroupRoleChanges extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 342; }
	public final String getName() { return "GroupRoleChanges"; }
	@Sequence(0)
	public GroupRoleChanges_bAgentData bagentdata=new GroupRoleChanges_bAgentData();
	@Sequence(1)
	public List<GroupRoleChanges_bRoleChange> brolechange;
	public GroupRoleChanges(){}
	public GroupRoleChanges(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
