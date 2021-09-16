package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.U32;

import javax.annotation.Nonnull;

public class RemoveTaskInventory_bInventoryData extends Block {
	@Nonnull
    @Sequence(0)
	public U32 vlocalid=new U32();
	@Nonnull
    @Sequence(1)
	public LLUUID vitemid=new LLUUID();
}
