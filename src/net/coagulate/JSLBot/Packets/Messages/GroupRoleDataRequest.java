package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GroupRoleDataRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 371; }
	public final String getName() { return "GroupRoleDataRequest"; }
	@Sequence(0)
	public GroupRoleDataRequest_bAgentData bagentdata=new GroupRoleDataRequest_bAgentData();
	@Sequence(1)
	public GroupRoleDataRequest_bGroupData bgroupdata=new GroupRoleDataRequest_bGroupData();
}
