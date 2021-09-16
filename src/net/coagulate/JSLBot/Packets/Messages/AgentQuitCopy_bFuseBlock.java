package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.U32;

import javax.annotation.Nonnull;

public class AgentQuitCopy_bFuseBlock extends Block {
	@Nonnull
    @Sequence(0)
	public U32 vviewercircuitcode=new U32();
}
