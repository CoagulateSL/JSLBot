package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.S32;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class RegionHandshake_bRegionInfo3 extends Block {
	@Nonnull
    @Sequence(0)
	public S32 vcpuclassid=new S32();
	@Nonnull
    @Sequence(1)
	public S32 vcpuratio=new S32();
	@Nonnull
    @Sequence(2)
	public Variable1 vcoloname=new Variable1();
	@Nonnull
    @Sequence(3)
	public Variable1 vproductsku=new Variable1();
	@Nonnull
    @Sequence(4)
	public Variable1 vproductname=new Variable1();
}
