package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLVector3;
import net.coagulate.JSLBot.Packets.Types.U64;

import javax.annotation.Nonnull;

public class TeleportLocationRequest_bInfo extends Block {
	@Nonnull
    @Sequence(0)
	public U64 vregionhandle=new U64();
	@Nonnull
    @Sequence(1)
	public LLVector3 vposition=new LLVector3();
	@Nonnull
    @Sequence(2)
	public LLVector3 vlookat=new LLVector3();
}
