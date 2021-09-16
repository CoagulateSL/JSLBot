package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.F32;

import javax.annotation.Nonnull;

public class ParcelJoin_bParcelData extends Block {
	@Nonnull
    @Sequence(0)
	public F32 vwest=new F32();
	@Nonnull
    @Sequence(1)
	public F32 vsouth=new F32();
	@Nonnull
    @Sequence(2)
	public F32 veast=new F32();
	@Nonnull
    @Sequence(3)
	public F32 vnorth=new F32();
}
