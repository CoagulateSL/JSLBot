package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectProperties_bObjectData extends Block {
	@Sequence(0)
	public LLUUID vobjectid=new LLUUID();
	@Sequence(1)
	public LLUUID vcreatorid=new LLUUID();
	@Sequence(2)
	public LLUUID vownerid=new LLUUID();
	@Sequence(3)
	public LLUUID vgroupid=new LLUUID();
	@Sequence(4)
	public U64 vcreationdate=new U64();
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
	public S32 vownershipcost=new S32();
	@Sequence(11)
	public U8 vsaletype=new U8();
	@Sequence(12)
	public S32 vsaleprice=new S32();
	@Sequence(13)
	public U8 vaggregateperms=new U8();
	@Sequence(14)
	public U8 vaggregatepermtextures=new U8();
	@Sequence(15)
	public U8 vaggregatepermtexturesowner=new U8();
	@Sequence(16)
	public U32 vcategory=new U32();
	@Sequence(17)
	public S16 vinventoryserial=new S16();
	@Sequence(18)
	public LLUUID vitemid=new LLUUID();
	@Sequence(19)
	public LLUUID vfolderid=new LLUUID();
	@Sequence(20)
	public LLUUID vfromtaskid=new LLUUID();
	@Sequence(21)
	public LLUUID vlastownerid=new LLUUID();
	@Sequence(22)
	public Variable1 vname=new Variable1();
	@Sequence(23)
	public Variable1 vdescription=new Variable1();
	@Sequence(24)
	public Variable1 vtouchname=new Variable1();
	@Sequence(25)
	public Variable1 vsitname=new Variable1();
	@Sequence(26)
	public Variable1 vtextureid=new Variable1();
}
