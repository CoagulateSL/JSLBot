package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class UpdateGroupInfo_bGroupData extends Block {
	@Sequence(0)
	public LLUUID vgroupid=new LLUUID();
	@Sequence(1)
	public Variable2 vcharter=new Variable2();
	@Sequence(2)
	public BOOL vshowinlist=new BOOL();
	@Sequence(3)
	public LLUUID vinsigniaid=new LLUUID();
	@Sequence(4)
	public S32 vmembershipfee=new S32();
	@Sequence(5)
	public BOOL vopenenrollment=new BOOL();
	@Sequence(6)
	public BOOL vallowpublish=new BOOL();
	@Sequence(7)
	public BOOL vmaturepublish=new BOOL();
}
