package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.F32;

import javax.annotation.Nonnull;

public class ModifyLand_bModifyBlockExtended extends Block {
	@Nonnull
    @Sequence(0)
	public F32 vbrushsize=new F32();
}
