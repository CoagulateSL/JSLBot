package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.BOOL;
import net.coagulate.JSLBot.Packets.Types.U32;

import javax.annotation.Nonnull;

public class RezScript_bUpdateBlock extends Block {
	@Nonnull
    @Sequence(0)
	public U32 vobjectlocalid=new U32();
	@Nonnull
    @Sequence(1)
	public BOOL venabled=new BOOL();
}
