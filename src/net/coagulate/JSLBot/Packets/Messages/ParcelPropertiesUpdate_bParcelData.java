package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.*;

import javax.annotation.Nonnull;

public class ParcelPropertiesUpdate_bParcelData extends Block {
	@Nonnull
    @Sequence(0)
	public S32 vlocalid=new S32();
	@Nonnull
    @Sequence(1)
	public U32 vflags=new U32();
	@Nonnull
    @Sequence(2)
	public U32 vparcelflags=new U32();
	@Nonnull
    @Sequence(3)
	public S32 vsaleprice=new S32();
	@Nonnull
    @Sequence(4)
	public Variable1 vname=new Variable1();
	@Nonnull
    @Sequence(5)
	public Variable1 vdesc=new Variable1();
	@Nonnull
    @Sequence(6)
	public Variable1 vmusicurl=new Variable1();
	@Nonnull
    @Sequence(7)
	public Variable1 vmediaurl=new Variable1();
	@Nonnull
    @Sequence(8)
	public LLUUID vmediaid=new LLUUID();
	@Nonnull
    @Sequence(9)
	public U8 vmediaautoscale=new U8();
	@Nonnull
    @Sequence(10)
	public LLUUID vgroupid=new LLUUID();
	@Nonnull
    @Sequence(11)
	public S32 vpassprice=new S32();
	@Nonnull
    @Sequence(12)
	public F32 vpasshours=new F32();
	@Nonnull
    @Sequence(13)
	public U8 vcategory=new U8();
	@Nonnull
    @Sequence(14)
	public LLUUID vauthbuyerid=new LLUUID();
	@Nonnull
    @Sequence(15)
	public LLUUID vsnapshotid=new LLUUID();
	@Nonnull
    @Sequence(16)
	public LLVector3 vuserlocation=new LLVector3();
	@Nonnull
    @Sequence(17)
	public LLVector3 vuserlookat=new LLVector3();
	@Nonnull
    @Sequence(18)
	public U8 vlandingtype=new U8();
}
