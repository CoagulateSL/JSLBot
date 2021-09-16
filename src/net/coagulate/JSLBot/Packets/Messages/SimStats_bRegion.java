package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.U32;

import javax.annotation.Nonnull;

public class SimStats_bRegion extends Block {
	@Nonnull
    @Sequence(0)
	public U32 vregionx=new U32();
	@Nonnull
    @Sequence(1)
	public U32 vregiony=new U32();
	@Nonnull
    @Sequence(2)
	public U32 vregionflags=new U32();
	@Nonnull
    @Sequence(3)
	public U32 vobjectcapacity=new U32();
}
