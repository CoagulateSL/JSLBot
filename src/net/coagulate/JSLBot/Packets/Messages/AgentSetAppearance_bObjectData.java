package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.Variable2;

import javax.annotation.Nonnull;

public class AgentSetAppearance_bObjectData extends Block {
	@Nonnull
    @Sequence(0)
	public Variable2 vtextureentry=new Variable2();
}
