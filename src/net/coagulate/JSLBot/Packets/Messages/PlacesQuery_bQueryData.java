package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.S8;
import net.coagulate.JSLBot.Packets.Types.U32;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class PlacesQuery_bQueryData extends Block {
	@Nonnull
    @Sequence(0)
	public Variable1 vquerytext=new Variable1();
	@Nonnull
    @Sequence(1)
	public U32 vqueryflags=new U32();
	@Nonnull
    @Sequence(2)
	public S8 vcategory=new S8();
	@Nonnull
    @Sequence(3)
	public Variable1 vsimname=new Variable1();
}
