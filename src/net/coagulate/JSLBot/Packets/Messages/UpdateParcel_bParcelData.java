package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.*;

import javax.annotation.Nonnull;

public class UpdateParcel_bParcelData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vparcelid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public U64 vregionhandle=new U64();
	@Nonnull
    @Sequence(2)
	public LLUUID vownerid=new LLUUID();
	@Nonnull
    @Sequence(3)
	public BOOL vgroupowned=new BOOL();
	@Nonnull
    @Sequence(4)
	public U8 vstatus=new U8();
	@Nonnull
    @Sequence(5)
	public Variable1 vname=new Variable1();
	@Nonnull
    @Sequence(6)
	public Variable1 vdescription=new Variable1();
	@Nonnull
    @Sequence(7)
	public Variable1 vmusicurl=new Variable1();
	@Nonnull
    @Sequence(8)
	public F32 vregionx=new F32();
	@Nonnull
    @Sequence(9)
	public F32 vregiony=new F32();
	@Nonnull
    @Sequence(10)
	public S32 vactualarea=new S32();
	@Nonnull
    @Sequence(11)
	public S32 vbillablearea=new S32();
	@Nonnull
    @Sequence(12)
	public BOOL vshowdir=new BOOL();
	@Nonnull
    @Sequence(13)
	public BOOL visforsale=new BOOL();
	@Nonnull
    @Sequence(14)
	public U8 vcategory=new U8();
	@Nonnull
    @Sequence(15)
	public LLUUID vsnapshotid=new LLUUID();
	@Nonnull
    @Sequence(16)
	public LLVector3 vuserlocation=new LLVector3();
	@Nonnull
    @Sequence(17)
	public S32 vsaleprice=new S32();
	@Nonnull
    @Sequence(18)
	public LLUUID vauthorizedbuyerid=new LLUUID();
	@Nonnull
    @Sequence(19)
	public BOOL vallowpublish=new BOOL();
	@Nonnull
    @Sequence(20)
	public BOOL vmaturepublish=new BOOL();
}
