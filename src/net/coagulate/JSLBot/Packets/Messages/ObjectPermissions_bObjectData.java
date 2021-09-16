package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.U32;
import net.coagulate.JSLBot.Packets.Types.U8;

import javax.annotation.Nonnull;

public class ObjectPermissions_bObjectData extends Block {
	@Nonnull
    @Sequence(0)
	public U32 vobjectlocalid=new U32();
	@Nonnull
    @Sequence(1)
	public U8 vfield=new U8();
	@Nonnull
    @Sequence(2)
	public U8 vset=new U8();
	@Nonnull
    @Sequence(3)
	public U32 vmask=new U32();
}
