package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.U32;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class RegionInfo_bRegionInfo2 extends Block {
	@Nonnull
    @Sequence(0)
	public Variable1 vproductsku=new Variable1();
	@Nonnull
    @Sequence(1)
	public Variable1 vproductname=new Variable1();
	@Nonnull
    @Sequence(2)
	public U32 vmaxagents32=new U32();
	@Nonnull
    @Sequence(3)
	public U32 vhardmaxagents=new U32();
	@Nonnull
    @Sequence(4)
	public U32 vhardmaxobjects=new U32();
}
