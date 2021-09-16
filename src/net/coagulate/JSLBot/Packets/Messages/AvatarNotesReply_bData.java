package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.Variable2;

import javax.annotation.Nonnull;

public class AvatarNotesReply_bData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vtargetid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public Variable2 vnotes=new Variable2();
}
