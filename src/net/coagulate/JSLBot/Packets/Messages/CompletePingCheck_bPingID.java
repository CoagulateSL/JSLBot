package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.U8;

import javax.annotation.Nonnull;

public class CompletePingCheck_bPingID extends Block {
	@Nonnull
    @Sequence(0)
	public U8 vpingid=new U8();
}
