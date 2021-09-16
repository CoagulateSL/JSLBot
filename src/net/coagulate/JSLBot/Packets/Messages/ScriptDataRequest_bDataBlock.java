package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.S8;
import net.coagulate.JSLBot.Packets.Types.U64;
import net.coagulate.JSLBot.Packets.Types.Variable2;

import javax.annotation.Nonnull;

public class ScriptDataRequest_bDataBlock extends Block {
	@Nonnull
    @Sequence(0)
	public U64 vhash=new U64();
	@Nonnull
    @Sequence(1)
	public S8 vrequesttype=new S8();
	@Nonnull
    @Sequence(2)
	public Variable2 vrequest=new Variable2();
}
