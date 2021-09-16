package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.S32;
import net.coagulate.JSLBot.Packets.Types.U32;
import net.coagulate.JSLBot.Packets.Types.U8;

import javax.annotation.Nonnull;

public class ObjectBuy_bObjectData extends Block {
	@Nonnull
    @Sequence(0)
	public U32 vobjectlocalid=new U32();
	@Nonnull
    @Sequence(1)
	public U8 vsaletype=new U8();
	@Nonnull
    @Sequence(2)
	public S32 vsaleprice=new S32();
}
