package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.Variable2;

import javax.annotation.Nonnull;

public class ChildAgentUpdate_bNVPairData extends Block {
	@Nonnull
    @Sequence(0)
	public Variable2 vnvpairs=new Variable2();
}
