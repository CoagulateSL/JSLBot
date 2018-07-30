package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GroupRoleUpdate extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 378; }
	public final String getName() { return "GroupRoleUpdate"; }
	@Sequence(0)
	public GroupRoleUpdate_bAgentData bagentdata=new GroupRoleUpdate_bAgentData();
	@Sequence(1)
	public List<GroupRoleUpdate_bRoleData> broledata;
}
