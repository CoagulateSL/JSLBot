package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.F32;
import net.coagulate.JSLBot.Packets.Types.S32;
import net.coagulate.JSLBot.Packets.Types.U32;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class GodUpdateRegionInfo_bRegionInfo extends Block {
	@Nonnull
    @Sequence(0)
	public Variable1 vsimname=new Variable1();
	@Nonnull
    @Sequence(1)
	public U32 vestateid=new U32();
	@Nonnull
    @Sequence(2)
	public U32 vparentestateid=new U32();
	@Nonnull
    @Sequence(3)
	public U32 vregionflags=new U32();
	@Nonnull
    @Sequence(4)
	public F32 vbillablefactor=new F32();
	@Nonnull
    @Sequence(5)
	public S32 vpricepermeter=new S32();
	@Nonnull
    @Sequence(6)
	public S32 vredirectgridx=new S32();
	@Nonnull
    @Sequence(7)
	public S32 vredirectgridy=new S32();
}
