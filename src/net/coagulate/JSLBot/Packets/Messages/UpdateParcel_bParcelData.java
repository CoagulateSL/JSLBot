package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class UpdateParcel_bParcelData extends Block {
	@Sequence(0)
	public LLUUID vparcelid=new LLUUID();
	@Sequence(1)
	public U64 vregionhandle=new U64();
	@Sequence(2)
	public LLUUID vownerid=new LLUUID();
	@Sequence(3)
	public BOOL vgroupowned=new BOOL();
	@Sequence(4)
	public U8 vstatus=new U8();
	@Sequence(5)
	public Variable1 vname=new Variable1();
	@Sequence(6)
	public Variable1 vdescription=new Variable1();
	@Sequence(7)
	public Variable1 vmusicurl=new Variable1();
	@Sequence(8)
	public F32 vregionx=new F32();
	@Sequence(9)
	public F32 vregiony=new F32();
	@Sequence(10)
	public S32 vactualarea=new S32();
	@Sequence(11)
	public S32 vbillablearea=new S32();
	@Sequence(12)
	public BOOL vshowdir=new BOOL();
	@Sequence(13)
	public BOOL visforsale=new BOOL();
	@Sequence(14)
	public U8 vcategory=new U8();
	@Sequence(15)
	public LLUUID vsnapshotid=new LLUUID();
	@Sequence(16)
	public LLVector3 vuserlocation=new LLVector3();
	@Sequence(17)
	public S32 vsaleprice=new S32();
	@Sequence(18)
	public LLUUID vauthorizedbuyerid=new LLUUID();
	@Sequence(19)
	public BOOL vallowpublish=new BOOL();
	@Sequence(20)
	public BOOL vmaturepublish=new BOOL();
}
