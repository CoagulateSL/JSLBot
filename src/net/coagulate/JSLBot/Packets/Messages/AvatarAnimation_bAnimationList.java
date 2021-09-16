package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.S32;

import javax.annotation.Nonnull;

public class AvatarAnimation_bAnimationList extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vanimid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public S32 vanimsequenceid=new S32();
}
