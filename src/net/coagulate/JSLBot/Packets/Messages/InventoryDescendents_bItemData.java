package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.*;

import javax.annotation.Nonnull;

public class InventoryDescendents_bItemData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vitemid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public LLUUID vfolderid=new LLUUID();
	@Nonnull
    @Sequence(2)
	public LLUUID vcreatorid=new LLUUID();
	@Nonnull
    @Sequence(3)
	public LLUUID vownerid=new LLUUID();
	@Nonnull
    @Sequence(4)
	public LLUUID vgroupid=new LLUUID();
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
	public BOOL vgroupowned=new BOOL();
	@Nonnull
    @Sequence(11)
	public LLUUID vassetid=new LLUUID();
	@Nonnull
    @Sequence(12)
	public S8 vtype=new S8();
	@Nonnull
    @Sequence(13)
	public S8 vinvtype=new S8();
	@Nonnull
    @Sequence(14)
	public U32 vflags=new U32();
	@Nonnull
    @Sequence(15)
	public U8 vsaletype=new U8();
	@Nonnull
    @Sequence(16)
	public S32 vsaleprice=new S32();
	@Nonnull
    @Sequence(17)
	public Variable1 vname=new Variable1();
	@Nonnull
    @Sequence(18)
	public Variable1 vdescription=new Variable1();
	@Nonnull
    @Sequence(19)
	public S32 vcreationdate=new S32();
	@Nonnull
    @Sequence(20)
	public U32 vcrc=new U32();
}
