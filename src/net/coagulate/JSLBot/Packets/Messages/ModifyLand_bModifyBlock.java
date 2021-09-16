package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.F32;
import net.coagulate.JSLBot.Packets.Types.U8;

import javax.annotation.Nonnull;

public class ModifyLand_bModifyBlock extends Block {
	@Nonnull
    @Sequence(0)
	public U8 vaction=new U8();
	@Nonnull
    @Sequence(1)
	public U8 vbrushsize=new U8();
	@Nonnull
    @Sequence(2)
	public F32 vseconds=new F32();
	@Nonnull
    @Sequence(3)
	public F32 vheight=new F32();
}
