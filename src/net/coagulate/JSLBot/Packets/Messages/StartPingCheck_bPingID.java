package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.U32;
import net.coagulate.JSLBot.Packets.Types.U8;

import javax.annotation.Nonnull;

public class StartPingCheck_bPingID extends Block {
	@Nonnull
    @Sequence(0)
	public U8 vpingid=new U8();
	@Nonnull
    @Sequence(1)
	public U32 voldestunacked=new U32();
}
