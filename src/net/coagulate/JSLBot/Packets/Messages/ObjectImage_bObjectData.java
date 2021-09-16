package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.U32;
import net.coagulate.JSLBot.Packets.Types.Variable1;
import net.coagulate.JSLBot.Packets.Types.Variable2;

import javax.annotation.Nonnull;

public class ObjectImage_bObjectData extends Block {
	@Nonnull
    @Sequence(0)
	public U32 vobjectlocalid=new U32();
	@Nonnull
    @Sequence(1)
	public Variable1 vmediaurl=new Variable1();
	@Nonnull
    @Sequence(2)
	public Variable2 vtextureentry=new Variable2();
}
