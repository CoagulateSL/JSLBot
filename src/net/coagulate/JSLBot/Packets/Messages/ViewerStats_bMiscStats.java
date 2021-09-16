package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.F64;
import net.coagulate.JSLBot.Packets.Types.U32;

import javax.annotation.Nonnull;

public class ViewerStats_bMiscStats extends Block {
	@Nonnull
    @Sequence(0)
	public U32 vtype=new U32();
	@Nonnull
    @Sequence(1)
	public F64 vvalue=new F64();
}
