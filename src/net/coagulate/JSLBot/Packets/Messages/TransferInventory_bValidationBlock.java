package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.BOOL;
import net.coagulate.JSLBot.Packets.Types.U32;

import javax.annotation.Nonnull;

public class TransferInventory_bValidationBlock extends Block {
	@Nonnull
    @Sequence(0)
	public BOOL vneedsvalidation=new BOOL();
	@Nonnull
    @Sequence(1)
	public U32 vestateid=new U32();
}
