package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;

import javax.annotation.Nonnull;

public class DenyTrustedCircuit_bDataBlock extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vendpointid=new LLUUID();
}
