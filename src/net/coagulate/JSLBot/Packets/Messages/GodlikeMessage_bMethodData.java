package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class GodlikeMessage_bMethodData extends Block {
	@Nonnull
    @Sequence(0)
	public Variable1 vmethod=new Variable1();
	@Nonnull
    @Sequence(1)
	public LLUUID vinvoice=new LLUUID();
}
