package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.S32;
import net.coagulate.JSLBot.Packets.Types.U32;
import net.coagulate.JSLBot.Packets.Types.U8;

import javax.annotation.Nonnull;

public class AvatarAppearance_bAppearanceData extends Block {
	@Nonnull
    @Sequence(0)
	public U8 vappearanceversion=new U8();
	@Nonnull
    @Sequence(1)
	public S32 vcofversion=new S32();
	@Nonnull
    @Sequence(2)
	public U32 vflags=new U32();
}
