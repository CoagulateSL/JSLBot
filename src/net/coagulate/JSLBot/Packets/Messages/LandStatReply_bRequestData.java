package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.U32;

import javax.annotation.Nonnull;

public class LandStatReply_bRequestData extends Block {
	@Nonnull
    @Sequence(0)
	public U32 vreporttype=new U32();
	@Nonnull
    @Sequence(1)
	public U32 vrequestflags=new U32();
	@Nonnull
    @Sequence(2)
	public U32 vtotalobjectcount=new U32();
}
