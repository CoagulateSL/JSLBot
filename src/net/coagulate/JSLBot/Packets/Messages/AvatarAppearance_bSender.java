package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.BOOL;
import net.coagulate.JSLBot.Packets.Types.LLUUID;

import javax.annotation.Nonnull;

public class AvatarAppearance_bSender extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public BOOL vistrial=new BOOL();
}
