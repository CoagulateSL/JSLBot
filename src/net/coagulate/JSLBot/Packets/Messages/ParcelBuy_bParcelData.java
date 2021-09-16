package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.S32;

import javax.annotation.Nonnull;

public class ParcelBuy_bParcelData extends Block {
	@Nonnull
    @Sequence(0)
	public S32 vprice=new S32();
	@Nonnull
    @Sequence(1)
	public S32 varea=new S32();
}
