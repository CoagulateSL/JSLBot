package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.*;

import javax.annotation.Nonnull;

public class DirClassifiedReply_bQueryReplies extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vclassifiedid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public Variable1 vname=new Variable1();
	@Nonnull
    @Sequence(2)
	public U8 vclassifiedflags=new U8();
	@Nonnull
    @Sequence(3)
	public U32 vcreationdate=new U32();
	@Nonnull
    @Sequence(4)
	public U32 vexpirationdate=new U32();
	@Nonnull
    @Sequence(5)
	public S32 vpriceforlisting=new S32();
}
