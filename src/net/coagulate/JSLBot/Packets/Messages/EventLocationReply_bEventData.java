package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.BOOL;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.LLVector3;

import javax.annotation.Nonnull;

public class EventLocationReply_bEventData extends Block {
	@Nonnull
    @Sequence(0)
	public BOOL vsuccess=new BOOL();
	@Nonnull
    @Sequence(1)
	public LLUUID vregionid=new LLUUID();
	@Nonnull
    @Sequence(2)
	public LLVector3 vregionpos=new LLVector3();
}
