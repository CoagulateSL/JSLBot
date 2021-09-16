package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.U32;

import javax.annotation.Nonnull;

public class RpcScriptRequestInbound_bTargetBlock extends Block {
	@Nonnull
    @Sequence(0)
	public U32 vgridx=new U32();
	@Nonnull
    @Sequence(1)
	public U32 vgridy=new U32();
}
