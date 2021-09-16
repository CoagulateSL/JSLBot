package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.U8;
import net.coagulate.JSLBot.Packets.Types.Variable2;

import javax.annotation.Nonnull;

public class EdgeDataPacket_bEdgeData extends Block {
	@Nonnull
    @Sequence(0)
	public U8 vlayertype=new U8();
	@Nonnull
    @Sequence(1)
	public U8 vdirection=new U8();
	@Nonnull
    @Sequence(2)
	public Variable2 vlayerdata=new Variable2();
}
