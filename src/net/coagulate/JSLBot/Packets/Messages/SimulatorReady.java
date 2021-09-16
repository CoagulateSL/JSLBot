package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class SimulatorReady extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 9; }
	@Nonnull
    public final String getName() { return "SimulatorReady"; }
	@Nonnull
    @Sequence(0)
	public SimulatorReady_bSimulatorBlock bsimulatorblock=new SimulatorReady_bSimulatorBlock();
	@Nonnull
    @Sequence(1)
	public SimulatorReady_bTelehubBlock btelehubblock=new SimulatorReady_bTelehubBlock();
}
