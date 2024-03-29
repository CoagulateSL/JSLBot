package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.*;

import javax.annotation.Nonnull;

public class ObjectProperties_bObjectData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vobjectid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public LLUUID vcreatorid=new LLUUID();
	@Nonnull
    @Sequence(2)
	public LLUUID vownerid=new LLUUID();
	@Nonnull
    @Sequence(3)
	public LLUUID vgroupid=new LLUUID();
	@Nonnull
    @Sequence(4)
	public U64 vcreationdate=new U64();
	@Nonnull
    @Sequence(5)
	public U32 vbasemask=new U32();
	@Nonnull
    @Sequence(6)
	public U32 vownermask=new U32();
	@Nonnull
    @Sequence(7)
	public U32 vgroupmask=new U32();
	@Nonnull
    @Sequence(8)
	public U32 veveryonemask=new U32();
	@Nonnull
    @Sequence(9)
	public U32 vnextownermask=new U32();
	@Nonnull
    @Sequence(10)
	public S32 vownershipcost=new S32();
	@Nonnull
    @Sequence(11)
	public U8 vsaletype=new U8();
	@Nonnull
    @Sequence(12)
	public S32 vsaleprice=new S32();
	@Nonnull
    @Sequence(13)
	public U8 vaggregateperms=new U8();
	@Nonnull
    @Sequence(14)
	public U8 vaggregatepermtextures=new U8();
	@Nonnull
    @Sequence(15)
	public U8 vaggregatepermtexturesowner=new U8();
	@Nonnull
    @Sequence(16)
	public U32 vcategory=new U32();
	@Nonnull
    @Sequence(17)
	public S16 vinventoryserial=new S16();
	@Nonnull
    @Sequence(18)
	public LLUUID vitemid=new LLUUID();
	@Nonnull
    @Sequence(19)
	public LLUUID vfolderid=new LLUUID();
	@Nonnull
    @Sequence(20)
	public LLUUID vfromtaskid=new LLUUID();
	@Nonnull
    @Sequence(21)
	public LLUUID vlastownerid=new LLUUID();
	@Nonnull
    @Sequence(22)
	public Variable1 vname=new Variable1();
	@Nonnull
    @Sequence(23)
	public Variable1 vdescription=new Variable1();
	@Nonnull
    @Sequence(24)
	public Variable1 vtouchname=new Variable1();
	@Nonnull
    @Sequence(25)
	public Variable1 vsitname=new Variable1();
	@Nonnull
    @Sequence(26)
	public Variable1 vtextureid=new Variable1();
}
