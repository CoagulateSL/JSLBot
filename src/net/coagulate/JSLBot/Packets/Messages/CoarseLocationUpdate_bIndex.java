package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.S16;

import javax.annotation.Nonnull;

public class CoarseLocationUpdate_bIndex extends Block {
	@Nonnull
    @Sequence(0)
	public S16 vyou=new S16();
	@Nonnull
    @Sequence(1)
	public S16 vprey=new S16();
}
