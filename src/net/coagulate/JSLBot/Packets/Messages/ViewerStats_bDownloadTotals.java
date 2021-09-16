package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.U32;

import javax.annotation.Nonnull;

public class ViewerStats_bDownloadTotals extends Block {
	@Nonnull
    @Sequence(0)
	public U32 vworld=new U32();
	@Nonnull
    @Sequence(1)
	public U32 vobjects=new U32();
	@Nonnull
    @Sequence(2)
	public U32 vtextures=new U32();
}
