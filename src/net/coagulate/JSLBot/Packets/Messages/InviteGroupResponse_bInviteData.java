package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class InviteGroupResponse_bInviteData extends Block {
	@Sequence(0)
	public LLUUID vagentid=new LLUUID();
	@Sequence(1)
	public LLUUID vinviteeid=new LLUUID();
	@Sequence(2)
	public LLUUID vgroupid=new LLUUID();
	@Sequence(3)
	public LLUUID vroleid=new LLUUID();
	@Sequence(4)
	public S32 vmembershipfee=new S32();
}
