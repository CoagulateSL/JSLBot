package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.S32;
import net.coagulate.JSLBot.Packets.Types.U64;

import javax.annotation.Nonnull;

public class AbortXfer_bXferID extends Block {
	@Nonnull
    @Sequence(0)
	public U64 vid=new U64();
	@Nonnull
    @Sequence(1)
	public S32 vresult=new S32();
}
