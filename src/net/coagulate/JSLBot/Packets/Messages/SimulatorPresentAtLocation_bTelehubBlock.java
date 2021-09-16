package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.BOOL;
import net.coagulate.JSLBot.Packets.Types.LLVector3;

import javax.annotation.Nonnull;

public class SimulatorPresentAtLocation_bTelehubBlock extends Block {
	@Nonnull
    @Sequence(0)
	public BOOL vhastelehub=new BOOL();
	@Nonnull
    @Sequence(1)
	public LLVector3 vtelehubpos=new LLVector3();
}
