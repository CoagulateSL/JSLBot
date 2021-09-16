package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.U8;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class StartLure_bInfo extends Block {
	@Nonnull
    @Sequence(0)
	public U8 vluretype=new U8();
	@Nonnull
    @Sequence(1)
	public Variable1 vmessage=new Variable1();
}
