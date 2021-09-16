package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.U32;

import javax.annotation.Nonnull;

public class ViewerStats_bNetStats extends Block {
	@Nonnull
    @Sequence(0)
	public U32 vbytes=new U32();
	@Nonnull
    @Sequence(1)
	public U32 vpackets=new U32();
	@Nonnull
    @Sequence(2)
	public U32 vcompressed=new U32();
	@Nonnull
    @Sequence(3)
	public U32 vsavings=new U32();
}
