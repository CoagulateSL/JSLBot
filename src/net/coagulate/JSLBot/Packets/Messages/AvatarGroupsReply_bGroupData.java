package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AvatarGroupsReply_bGroupData extends Block {
	@Sequence(0)
	public U64 vgrouppowers=new U64();
	@Sequence(1)
	public BOOL vacceptnotices=new BOOL();
	@Sequence(2)
	public Variable1 vgrouptitle=new Variable1();
	@Sequence(3)
	public LLUUID vgroupid=new LLUUID();
	@Sequence(4)
	public Variable1 vgroupname=new Variable1();
	@Sequence(5)
	public LLUUID vgroupinsigniaid=new LLUUID();
}
