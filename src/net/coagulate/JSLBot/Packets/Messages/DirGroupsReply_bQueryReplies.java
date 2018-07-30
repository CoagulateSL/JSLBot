package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class DirGroupsReply_bQueryReplies extends Block {
	@Sequence(0)
	public LLUUID vgroupid=new LLUUID();
	@Sequence(1)
	public Variable1 vgroupname=new Variable1();
	@Sequence(2)
	public S32 vmembers=new S32();
	@Sequence(3)
	public F32 vsearchorder=new F32();
}
