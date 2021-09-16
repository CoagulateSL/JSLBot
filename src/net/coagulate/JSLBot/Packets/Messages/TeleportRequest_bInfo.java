package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.LLVector3;

import javax.annotation.Nonnull;

public class TeleportRequest_bInfo extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vregionid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public LLVector3 vposition=new LLVector3();
	@Nonnull
    @Sequence(2)
	public LLVector3 vlookat=new LLVector3();
}
