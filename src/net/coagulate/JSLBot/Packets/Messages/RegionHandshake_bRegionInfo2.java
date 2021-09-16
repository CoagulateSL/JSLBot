package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;

import javax.annotation.Nonnull;

public class RegionHandshake_bRegionInfo2 extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vregionid=new LLUUID();
}
