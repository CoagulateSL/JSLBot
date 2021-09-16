package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.U8;

import javax.annotation.Nonnull;

public class LayerData_bLayerID extends Block {
	@Nonnull
    @Sequence(0)
	public U8 vtype=new U8();
}
