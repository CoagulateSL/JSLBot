package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.BOOL;
import net.coagulate.JSLBot.Packets.Types.F32;
import net.coagulate.JSLBot.Packets.Types.S32;

import javax.annotation.Nonnull;

public class SimulatorLoad_bSimulatorLoad extends Block {
	@Nonnull
    @Sequence(0)
	public F32 vtimedilation=new F32();
	@Nonnull
    @Sequence(1)
	public S32 vagentcount=new S32();
	@Nonnull
    @Sequence(2)
	public BOOL vcanacceptagents=new BOOL();
}
