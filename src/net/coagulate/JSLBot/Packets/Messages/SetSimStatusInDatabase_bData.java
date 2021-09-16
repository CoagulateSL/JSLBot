package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.S32;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class SetSimStatusInDatabase_bData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vregionid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public Variable1 vhostname=new Variable1();
	@Nonnull
    @Sequence(2)
	public S32 vx=new S32();
	@Nonnull
    @Sequence(3)
	public S32 vy=new S32();
	@Nonnull
    @Sequence(4)
	public S32 vpid=new S32();
	@Nonnull
    @Sequence(5)
	public S32 vagentcount=new S32();
	@Nonnull
    @Sequence(6)
	public S32 vtimetolive=new S32();
	@Nonnull
    @Sequence(7)
	public Variable1 vstatus=new Variable1();
}
