package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.BOOL;

import javax.annotation.Nonnull;

public class UpdateAttachment_bOperationData extends Block {
	@Nonnull
    @Sequence(0)
	public BOOL vadditem=new BOOL();
	@Nonnull
    @Sequence(1)
	public BOOL vuseexistingasset=new BOOL();
}
