package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.S32;
import net.coagulate.JSLBot.Packets.Types.U32;

import javax.annotation.Nonnull;

public class ParcelAccessListReply_bList extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public S32 vtime=new S32();
	@Nonnull
    @Sequence(2)
	public U32 vflags=new U32();
}
