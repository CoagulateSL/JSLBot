package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.U32;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class DirEventsReply_bQueryReplies extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vownerid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public Variable1 vname=new Variable1();
	@Nonnull
    @Sequence(2)
	public U32 veventid=new U32();
	@Nonnull
    @Sequence(3)
	public Variable1 vdate=new Variable1();
	@Nonnull
    @Sequence(4)
	public U32 vunixtime=new U32();
	@Nonnull
    @Sequence(5)
	public U32 veventflags=new U32();
}
