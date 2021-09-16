package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.IPADDR;
import net.coagulate.JSLBot.Packets.Types.IPPORT;
import net.coagulate.JSLBot.Packets.Types.U64;

import javax.annotation.Nonnull;

public class EnableSimulator_bSimulatorInfo extends Block {
	@Nonnull
    @Sequence(0)
	public U64 vhandle=new U64();
	@Nonnull
    @Sequence(1)
	public IPADDR vip=new IPADDR();
	@Nonnull
    @Sequence(2)
	public IPPORT vport=new IPPORT();
}
