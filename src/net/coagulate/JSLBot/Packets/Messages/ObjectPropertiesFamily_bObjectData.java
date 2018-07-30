package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectPropertiesFamily_bObjectData extends Block {
	@Sequence(0)
	public U32 vrequestflags=new U32();
	@Sequence(1)
	public LLUUID vobjectid=new LLUUID();
	@Sequence(2)
	public LLUUID vownerid=new LLUUID();
	@Sequence(3)
	public LLUUID vgroupid=new LLUUID();
	@Sequence(4)
	public U32 vbasemask=new U32();
	@Sequence(5)
	public U32 vownermask=new U32();
	@Sequence(6)
	public U32 vgroupmask=new U32();
	@Sequence(7)
	public U32 veveryonemask=new U32();
	@Sequence(8)
	public U32 vnextownermask=new U32();
	@Sequence(9)
	public S32 vownershipcost=new S32();
	@Sequence(10)
	public U8 vsaletype=new U8();
	@Sequence(11)
	public S32 vsaleprice=new S32();
	@Sequence(12)
	public U32 vcategory=new U32();
	@Sequence(13)
	public LLUUID vlastownerid=new LLUUID();
	@Sequence(14)
	public Variable1 vname=new Variable1();
	@Sequence(15)
	public Variable1 vdescription=new Variable1();
}
