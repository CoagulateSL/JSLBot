package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLVector3;

import javax.annotation.Nonnull;

public class AvatarAppearance_bAppearanceHover extends Block {
	@Nonnull
    @Sequence(0)
	public LLVector3 vhoverheight=new LLVector3();
}
