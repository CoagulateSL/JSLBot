package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.U32;
import net.coagulate.JSLBot.Packets.Types.U8;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class SimulatorReady_bSimulatorBlock extends Block {
	@Nonnull
    @Sequence(0)
	public Variable1 vsimname=new Variable1();
	@Nonnull
    @Sequence(1)
	public U8 vsimaccess=new U8();
	@Nonnull
    @Sequence(2)
	public U32 vregionflags=new U32();
	@Nonnull
    @Sequence(3)
	public LLUUID vregionid=new LLUUID();
	@Nonnull
    @Sequence(4)
	public U32 vestateid=new U32();
	@Nonnull
    @Sequence(5)
	public U32 vparentestateid=new U32();
}
