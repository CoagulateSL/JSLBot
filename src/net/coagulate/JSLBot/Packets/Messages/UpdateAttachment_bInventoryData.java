package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class UpdateAttachment_bInventoryData extends Block {
	@Sequence(0)
	public LLUUID vitemid=new LLUUID();
	@Sequence(1)
	public LLUUID vfolderid=new LLUUID();
	@Sequence(2)
	public LLUUID vcreatorid=new LLUUID();
	@Sequence(3)
	public LLUUID vownerid=new LLUUID();
	@Sequence(4)
	public LLUUID vgroupid=new LLUUID();
	@Sequence(5)
	public U32 vbasemask=new U32();
	@Sequence(6)
	public U32 vownermask=new U32();
	@Sequence(7)
	public U32 vgroupmask=new U32();
	@Sequence(8)
	public U32 veveryonemask=new U32();
	@Sequence(9)
	public U32 vnextownermask=new U32();
	@Sequence(10)
	public BOOL vgroupowned=new BOOL();
	@Sequence(11)
	public LLUUID vassetid=new LLUUID();
	@Sequence(12)
	public S8 vtype=new S8();
	@Sequence(13)
	public S8 vinvtype=new S8();
	@Sequence(14)
	public U32 vflags=new U32();
	@Sequence(15)
	public U8 vsaletype=new U8();
	@Sequence(16)
	public S32 vsaleprice=new S32();
	@Sequence(17)
	public Variable1 vname=new Variable1();
	@Sequence(18)
	public Variable1 vdescription=new Variable1();
	@Sequence(19)
	public S32 vcreationdate=new S32();
	@Sequence(20)
	public U32 vcrc=new U32();
}
