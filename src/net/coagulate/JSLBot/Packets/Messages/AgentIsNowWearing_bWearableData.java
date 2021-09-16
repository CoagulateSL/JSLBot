package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.U8;

import javax.annotation.Nonnull;

public class AgentIsNowWearing_bWearableData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vitemid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public U8 vwearabletype=new U8();
}
