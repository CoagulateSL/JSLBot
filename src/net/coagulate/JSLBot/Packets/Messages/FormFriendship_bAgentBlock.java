package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;

import javax.annotation.Nonnull;

public class FormFriendship_bAgentBlock extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vsourceid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public LLUUID vdestid=new LLUUID();
}
