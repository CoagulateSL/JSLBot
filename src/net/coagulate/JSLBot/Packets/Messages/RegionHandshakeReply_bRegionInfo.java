package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.U32;

import javax.annotation.Nonnull;

public class RegionHandshakeReply_bRegionInfo extends Block {
	@Nonnull
    @Sequence(0)
	public U32 vflags=new U32();
}
