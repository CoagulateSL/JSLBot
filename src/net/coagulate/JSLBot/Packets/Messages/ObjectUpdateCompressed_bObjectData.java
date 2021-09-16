package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.U32;
import net.coagulate.JSLBot.Packets.Types.Variable2;

import javax.annotation.Nonnull;

public class ObjectUpdateCompressed_bObjectData extends Block {
	@Nonnull
    @Sequence(0)
	public U32 vupdateflags=new U32();
	@Nonnull
    @Sequence(1)
	public Variable2 vdata=new Variable2();
}
