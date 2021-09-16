package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.U16;

import javax.annotation.Nonnull;

public class MapBlockRequest_bPositionData extends Block {
	@Nonnull
    @Sequence(0)
	public U16 vminx=new U16();
	@Nonnull
    @Sequence(1)
	public U16 vmaxx=new U16();
	@Nonnull
    @Sequence(2)
	public U16 vminy=new U16();
	@Nonnull
    @Sequence(3)
	public U16 vmaxy=new U16();
}
