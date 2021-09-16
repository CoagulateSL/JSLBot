package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.U32;
import net.coagulate.JSLBot.Packets.Types.U8;

import javax.annotation.Nonnull;

public class RequestMultipleObjects_bObjectData extends Block {
	@Nonnull
    @Sequence(0)
	public U8 vcachemisstype=new U8();
	@Nonnull
    @Sequence(1)
	public U32 vid=new U32();
}
