package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GroupRoleDataReply_bGroupData extends Block {
	@Sequence(0)
	public LLUUID vgroupid=new LLUUID();
	@Sequence(1)
	public LLUUID vrequestid=new LLUUID();
	@Sequence(2)
	public S32 vrolecount=new S32();
}
