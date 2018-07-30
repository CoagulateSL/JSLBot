package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GroupRoleMembersReply_bAgentData extends Block {
	@Sequence(0)
	public LLUUID vagentid=new LLUUID();
	@Sequence(1)
	public LLUUID vgroupid=new LLUUID();
	@Sequence(2)
	public LLUUID vrequestid=new LLUUID();
	@Sequence(3)
	public U32 vtotalpairs=new U32();
}
