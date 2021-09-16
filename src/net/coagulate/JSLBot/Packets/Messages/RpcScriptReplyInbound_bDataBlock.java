package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.U32;
import net.coagulate.JSLBot.Packets.Types.Variable2;

import javax.annotation.Nonnull;

public class RpcScriptReplyInbound_bDataBlock extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vtaskid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public LLUUID vitemid=new LLUUID();
	@Nonnull
    @Sequence(2)
	public LLUUID vchannelid=new LLUUID();
	@Nonnull
    @Sequence(3)
	public U32 vintvalue=new U32();
	@Nonnull
    @Sequence(4)
	public Variable2 vstringvalue=new Variable2();
}
