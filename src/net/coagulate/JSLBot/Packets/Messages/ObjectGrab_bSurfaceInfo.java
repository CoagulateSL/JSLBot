package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLVector3;
import net.coagulate.JSLBot.Packets.Types.S32;

import javax.annotation.Nonnull;

public class ObjectGrab_bSurfaceInfo extends Block {
	@Nonnull
    @Sequence(0)
	public LLVector3 vuvcoord=new LLVector3();
	@Nonnull
    @Sequence(1)
	public LLVector3 vstcoord=new LLVector3();
	@Nonnull
    @Sequence(2)
	public S32 vfaceindex=new S32();
	@Nonnull
    @Sequence(3)
	public LLVector3 vposition=new LLVector3();
	@Nonnull
    @Sequence(4)
	public LLVector3 vnormal=new LLVector3();
	@Nonnull
    @Sequence(5)
	public LLVector3 vbinormal=new LLVector3();
}
