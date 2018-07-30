package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GroupProfileReply_bGroupData extends Block {
	@Sequence(0)
	public LLUUID vgroupid=new LLUUID();
	@Sequence(1)
	public Variable1 vname=new Variable1();
	@Sequence(2)
	public Variable2 vcharter=new Variable2();
	@Sequence(3)
	public BOOL vshowinlist=new BOOL();
	@Sequence(4)
	public Variable1 vmembertitle=new Variable1();
	@Sequence(5)
	public U64 vpowersmask=new U64();
	@Sequence(6)
	public LLUUID vinsigniaid=new LLUUID();
	@Sequence(7)
	public LLUUID vfounderid=new LLUUID();
	@Sequence(8)
	public S32 vmembershipfee=new S32();
	@Sequence(9)
	public BOOL vopenenrollment=new BOOL();
	@Sequence(10)
	public S32 vmoney=new S32();
	@Sequence(11)
	public S32 vgroupmembershipcount=new S32();
	@Sequence(12)
	public S32 vgrouprolescount=new S32();
	@Sequence(13)
	public BOOL vallowpublish=new BOOL();
	@Sequence(14)
	public BOOL vmaturepublish=new BOOL();
	@Sequence(15)
	public LLUUID vownerrole=new LLUUID();
}
