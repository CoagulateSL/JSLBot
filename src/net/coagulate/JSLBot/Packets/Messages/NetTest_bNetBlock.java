package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.IPPORT;

import javax.annotation.Nonnull;

public class NetTest_bNetBlock extends Block {
	@Nonnull
    @Sequence(0)
	public IPPORT vport=new IPPORT();
}
