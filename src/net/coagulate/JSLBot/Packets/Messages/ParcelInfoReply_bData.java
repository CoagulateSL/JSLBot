package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.*;

import javax.annotation.Nonnull;

public class ParcelInfoReply_bData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vparcelid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public LLUUID vownerid=new LLUUID();
	@Nonnull
    @Sequence(2)
	public Variable1 vname=new Variable1();
	@Nonnull
    @Sequence(3)
	public Variable1 vdesc=new Variable1();
	@Nonnull
    @Sequence(4)
	public S32 vactualarea=new S32();
	@Nonnull
    @Sequence(5)
	public S32 vbillablearea=new S32();
	@Nonnull
    @Sequence(6)
	public U8 vflags=new U8();
	@Nonnull
    @Sequence(7)
	public F32 vglobalx=new F32();
	@Nonnull
    @Sequence(8)
	public F32 vglobaly=new F32();
	@Nonnull
    @Sequence(9)
	public F32 vglobalz=new F32();
	@Nonnull
    @Sequence(10)
	public Variable1 vsimname=new Variable1();
	@Nonnull
    @Sequence(11)
	public LLUUID vsnapshotid=new LLUUID();
	@Nonnull
    @Sequence(12)
	public F32 vdwell=new F32();
	@Nonnull
    @Sequence(13)
	public S32 vsaleprice=new S32();
	@Nonnull
    @Sequence(14)
	public S32 vauctionid=new S32();
}
