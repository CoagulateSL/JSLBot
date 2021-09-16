package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.U32;

import javax.annotation.Nonnull;

public class ObjectUpdateCached_bObjectData extends Block {
	@Nonnull
    @Sequence(0)
	public U32 vid=new U32();
	@Nonnull
    @Sequence(1)
	public U32 vcrc=new U32();
	@Nonnull
    @Sequence(2)
	public U32 vupdateflags=new U32();
}
