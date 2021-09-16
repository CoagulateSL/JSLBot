package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.Fixed32;
import net.coagulate.JSLBot.Packets.Types.LLUUID;

import javax.annotation.Nonnull;

public class CreateTrustedCircuit_bDataBlock extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vendpointid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public Fixed32 vdigest=new Fixed32();
}
