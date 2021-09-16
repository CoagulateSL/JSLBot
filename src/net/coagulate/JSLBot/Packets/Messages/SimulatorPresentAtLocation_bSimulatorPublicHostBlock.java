package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.IPADDR;
import net.coagulate.JSLBot.Packets.Types.IPPORT;
import net.coagulate.JSLBot.Packets.Types.U32;

import javax.annotation.Nonnull;

public class SimulatorPresentAtLocation_bSimulatorPublicHostBlock extends Block {
	@Nonnull
    @Sequence(0)
	public IPPORT vport=new IPPORT();
	@Nonnull
    @Sequence(1)
	public IPADDR vsimulatorip=new IPADDR();
	@Nonnull
    @Sequence(2)
	public U32 vgridx=new U32();
	@Nonnull
    @Sequence(3)
	public U32 vgridy=new U32();
}
