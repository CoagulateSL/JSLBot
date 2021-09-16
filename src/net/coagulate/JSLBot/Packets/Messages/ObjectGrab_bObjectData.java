package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLVector3;
import net.coagulate.JSLBot.Packets.Types.U32;

import javax.annotation.Nonnull;

public class ObjectGrab_bObjectData extends Block {
	@Nonnull
    @Sequence(0)
	public U32 vlocalid=new U32();
	@Nonnull
    @Sequence(1)
	public LLVector3 vgraboffset=new LLVector3();
}
