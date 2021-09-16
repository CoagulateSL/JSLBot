package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.U64;
import net.coagulate.JSLBot.Packets.Types.Variable2;

import javax.annotation.Nonnull;

public class ScriptDataReply_bDataBlock extends Block {
	@Nonnull
    @Sequence(0)
	public U64 vhash=new U64();
	@Nonnull
    @Sequence(1)
	public Variable2 vreply=new Variable2();
}
