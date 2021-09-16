package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLVector3;
import net.coagulate.JSLBot.Packets.Types.U32;

import javax.annotation.Nonnull;

public class ObjectDuplicate_bSharedData extends Block {
	@Nonnull
    @Sequence(0)
	public LLVector3 voffset=new LLVector3();
	@Nonnull
    @Sequence(1)
	public U32 vduplicateflags=new U32();
}
