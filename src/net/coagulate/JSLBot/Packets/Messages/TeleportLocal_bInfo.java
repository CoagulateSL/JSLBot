package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.LLVector3;
import net.coagulate.JSLBot.Packets.Types.U32;

import javax.annotation.Nonnull;

public class TeleportLocal_bInfo extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vagentid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public U32 vlocationid=new U32();
	@Nonnull
    @Sequence(2)
	public LLVector3 vposition=new LLVector3();
	@Nonnull
    @Sequence(3)
	public LLVector3 vlookat=new LLVector3();
	@Nonnull
    @Sequence(4)
	public U32 vteleportflags=new U32();
}
