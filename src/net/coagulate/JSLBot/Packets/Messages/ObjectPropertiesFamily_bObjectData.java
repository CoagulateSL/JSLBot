package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.*;

import javax.annotation.Nonnull;

public class ObjectPropertiesFamily_bObjectData extends Block {
	@Nonnull
    @Sequence(0)
	public U32 vrequestflags=new U32();
	@Nonnull
    @Sequence(1)
	public LLUUID vobjectid=new LLUUID();
	@Nonnull
    @Sequence(2)
	public LLUUID vownerid=new LLUUID();
	@Nonnull
    @Sequence(3)
	public LLUUID vgroupid=new LLUUID();
	@Nonnull
    @Sequence(4)
	public U32 vbasemask=new U32();
	@Nonnull
    @Sequence(5)
	public U32 vownermask=new U32();
	@Nonnull
    @Sequence(6)
	public U32 vgroupmask=new U32();
	@Nonnull
    @Sequence(7)
	public U32 veveryonemask=new U32();
	@Nonnull
    @Sequence(8)
	public U32 vnextownermask=new U32();
	@Nonnull
    @Sequence(9)
	public S32 vownershipcost=new S32();
	@Nonnull
    @Sequence(10)
	public U8 vsaletype=new U8();
	@Nonnull
    @Sequence(11)
	public S32 vsaleprice=new S32();
	@Nonnull
    @Sequence(12)
	public U32 vcategory=new U32();
	@Nonnull
    @Sequence(13)
	public LLUUID vlastownerid=new LLUUID();
	@Nonnull
    @Sequence(14)
	public Variable1 vname=new Variable1();
	@Nonnull
    @Sequence(15)
	public Variable1 vdescription=new Variable1();
}
