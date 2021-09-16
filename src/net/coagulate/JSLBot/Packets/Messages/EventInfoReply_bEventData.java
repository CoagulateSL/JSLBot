package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLVector3d;
import net.coagulate.JSLBot.Packets.Types.U32;
import net.coagulate.JSLBot.Packets.Types.Variable1;
import net.coagulate.JSLBot.Packets.Types.Variable2;

import javax.annotation.Nonnull;

public class EventInfoReply_bEventData extends Block {
	@Nonnull
    @Sequence(0)
	public U32 veventid=new U32();
	@Nonnull
    @Sequence(1)
	public Variable1 vcreator=new Variable1();
	@Nonnull
    @Sequence(2)
	public Variable1 vname=new Variable1();
	@Nonnull
    @Sequence(3)
	public Variable1 vcategory=new Variable1();
	@Nonnull
    @Sequence(4)
	public Variable2 vdesc=new Variable2();
	@Nonnull
    @Sequence(5)
	public Variable1 vdate=new Variable1();
	@Nonnull
    @Sequence(6)
	public U32 vdateutc=new U32();
	@Nonnull
    @Sequence(7)
	public U32 vduration=new U32();
	@Nonnull
    @Sequence(8)
	public U32 vcover=new U32();
	@Nonnull
    @Sequence(9)
	public U32 vamount=new U32();
	@Nonnull
    @Sequence(10)
	public Variable1 vsimname=new Variable1();
	@Nonnull
    @Sequence(11)
	public LLVector3d vglobalpos=new LLVector3d();
	@Nonnull
    @Sequence(12)
	public U32 veventflags=new U32();
}
