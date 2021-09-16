package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.U32;

import javax.annotation.Nonnull;

public class RequestTaskInventory_bInventoryData extends Block {
	@Nonnull
    @Sequence(0)
	public U32 vlocalid=new U32();
}
