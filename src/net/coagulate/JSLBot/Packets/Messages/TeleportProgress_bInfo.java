package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.U32;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class TeleportProgress_bInfo extends Block {
	@Nonnull
    @Sequence(0)
	public U32 vteleportflags=new U32();
	@Nonnull
    @Sequence(1)
	public Variable1 vmessage=new Variable1();
}
