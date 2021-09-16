package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.U16;
import net.coagulate.JSLBot.Packets.Types.U32;

import javax.annotation.Nonnull;

public class AgentHeightWidth_bHeightWidthBlock extends Block {
	@Nonnull
    @Sequence(0)
	public U32 vgencounter=new U32();
	@Nonnull
    @Sequence(1)
	public U16 vheight=new U16();
	@Nonnull
    @Sequence(2)
	public U16 vwidth=new U16();
}
