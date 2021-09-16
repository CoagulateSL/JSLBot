package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.F32;
import net.coagulate.JSLBot.Packets.Types.U32;

import javax.annotation.Nonnull;

public class AgentFOV_bFOVBlock extends Block {
	@Nonnull
    @Sequence(0)
	public U32 vgencounter=new U32();
	@Nonnull
    @Sequence(1)
	public F32 vverticalangle=new F32();
}
