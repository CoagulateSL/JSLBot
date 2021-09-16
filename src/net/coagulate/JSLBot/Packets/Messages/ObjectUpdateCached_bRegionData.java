package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.U16;
import net.coagulate.JSLBot.Packets.Types.U64;

import javax.annotation.Nonnull;

public class ObjectUpdateCached_bRegionData extends Block {
	@Nonnull
    @Sequence(0)
	public U64 vregionhandle=new U64();
	@Nonnull
    @Sequence(1)
	public U16 vtimedilation=new U16();
}
