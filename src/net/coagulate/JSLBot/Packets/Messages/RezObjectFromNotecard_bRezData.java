package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.*;

import javax.annotation.Nonnull;

public class RezObjectFromNotecard_bRezData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vfromtaskid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public U8 vbypassraycast=new U8();
	@Nonnull
    @Sequence(2)
	public LLVector3 vraystart=new LLVector3();
	@Nonnull
    @Sequence(3)
	public LLVector3 vrayend=new LLVector3();
	@Nonnull
    @Sequence(4)
	public LLUUID vraytargetid=new LLUUID();
	@Nonnull
    @Sequence(5)
	public BOOL vrayendisintersection=new BOOL();
	@Nonnull
    @Sequence(6)
	public BOOL vrezselected=new BOOL();
	@Nonnull
    @Sequence(7)
	public BOOL vremoveitem=new BOOL();
	@Nonnull
    @Sequence(8)
	public U32 vitemflags=new U32();
	@Nonnull
    @Sequence(9)
	public U32 vgroupmask=new U32();
	@Nonnull
    @Sequence(10)
	public U32 veveryonemask=new U32();
	@Nonnull
    @Sequence(11)
	public U32 vnextownermask=new U32();
}
