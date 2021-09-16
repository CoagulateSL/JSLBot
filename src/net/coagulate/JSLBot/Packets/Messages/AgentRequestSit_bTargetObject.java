package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.LLVector3;

import javax.annotation.Nonnull;

public class AgentRequestSit_bTargetObject extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vtargetid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public LLVector3 voffset=new LLVector3();
}
