package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.U64;

import javax.annotation.Nonnull;

public class RegionHandshake_bRegionInfo4 extends Block {
	@Nonnull
    @Sequence(0)
	public U64 vregionflagsextended=new U64();
	@Nonnull
    @Sequence(1)
	public U64 vregionprotocols=new U64();
}
