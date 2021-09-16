package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.F64;

import javax.annotation.Nonnull;

public class FindAgent_bLocationBlock extends Block {
	@Nonnull
    @Sequence(0)
	public F64 vglobalx=new F64();
	@Nonnull
    @Sequence(1)
	public F64 vglobaly=new F64();
}
