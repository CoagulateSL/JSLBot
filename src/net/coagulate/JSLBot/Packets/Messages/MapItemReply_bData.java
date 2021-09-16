package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.S32;
import net.coagulate.JSLBot.Packets.Types.U32;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class MapItemReply_bData extends Block {
	@Nonnull
    @Sequence(0)
	public U32 vx=new U32();
	@Nonnull
    @Sequence(1)
	public U32 vy=new U32();
	@Nonnull
    @Sequence(2)
	public LLUUID vid=new LLUUID();
	@Nonnull
    @Sequence(3)
	public S32 vextra=new S32();
	@Nonnull
    @Sequence(4)
	public S32 vextra2=new S32();
	@Nonnull
    @Sequence(5)
	public Variable1 vname=new Variable1();
}
