package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.F32;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.U32;
import net.coagulate.JSLBot.Packets.Types.U8;

import javax.annotation.Nonnull;

public class MeanCollisionAlert_bMeanCollision extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vvictim=new LLUUID();
	@Nonnull
    @Sequence(1)
	public LLUUID vperp=new LLUUID();
	@Nonnull
    @Sequence(2)
	public U32 vtime=new U32();
	@Nonnull
    @Sequence(3)
	public F32 vmag=new F32();
	@Nonnull
    @Sequence(4)
	public U8 vtype=new U8();
}
