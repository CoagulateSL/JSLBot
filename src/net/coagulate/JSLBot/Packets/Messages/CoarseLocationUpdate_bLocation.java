package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.U8;

import javax.annotation.Nonnull;

public class CoarseLocationUpdate_bLocation extends Block {
	@Nonnull
    @Sequence(0)
	public U8 vx=new U8();
	@Nonnull
    @Sequence(1)
	public U8 vy=new U8();
	@Nonnull
    @Sequence(2)
	public U8 vz=new U8();
}
