package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.S32;

import javax.annotation.Nonnull;

public class ParcelBuyPass_bParcelData extends Block {
	@Nonnull
    @Sequence(0)
	public S32 vlocalid=new S32();
}
