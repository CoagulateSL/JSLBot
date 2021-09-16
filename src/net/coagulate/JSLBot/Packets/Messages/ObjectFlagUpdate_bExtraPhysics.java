package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.F32;
import net.coagulate.JSLBot.Packets.Types.U8;

import javax.annotation.Nonnull;

public class ObjectFlagUpdate_bExtraPhysics extends Block {
	@Nonnull
    @Sequence(0)
	public U8 vphysicsshapetype=new U8();
	@Nonnull
    @Sequence(1)
	public F32 vdensity=new F32();
	@Nonnull
    @Sequence(2)
	public F32 vfriction=new F32();
	@Nonnull
    @Sequence(3)
	public F32 vrestitution=new F32();
	@Nonnull
    @Sequence(4)
	public F32 vgravitymultiplier=new F32();
}
