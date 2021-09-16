package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.U64;

import javax.annotation.Nonnull;

public class RegionIDAndHandleReply_bReplyBlock extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vregionid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public U64 vregionhandle=new U64();
}
