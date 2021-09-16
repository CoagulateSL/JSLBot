package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.U32;

import javax.annotation.Nonnull;

public class ViewerStats_bFailStats extends Block {
	@Nonnull
    @Sequence(0)
	public U32 vsendpacket=new U32();
	@Nonnull
    @Sequence(1)
	public U32 vdropped=new U32();
	@Nonnull
    @Sequence(2)
	public U32 vresent=new U32();
	@Nonnull
    @Sequence(3)
	public U32 vfailedresends=new U32();
	@Nonnull
    @Sequence(4)
	public U32 voffcircuit=new U32();
	@Nonnull
    @Sequence(5)
	public U32 vinvalid=new U32();
}
