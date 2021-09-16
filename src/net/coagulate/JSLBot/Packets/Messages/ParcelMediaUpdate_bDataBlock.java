package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.U8;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class ParcelMediaUpdate_bDataBlock extends Block {
	@Nonnull
    @Sequence(0)
	public Variable1 vmediaurl=new Variable1();
	@Nonnull
    @Sequence(1)
	public LLUUID vmediaid=new LLUUID();
	@Nonnull
    @Sequence(2)
	public U8 vmediaautoscale=new U8();
}
