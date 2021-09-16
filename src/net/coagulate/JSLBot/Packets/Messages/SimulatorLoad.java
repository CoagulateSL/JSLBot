package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class SimulatorLoad extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 12; }
	@Nonnull
    public final String getName() { return "SimulatorLoad"; }
	@Nonnull
    @Sequence(0)
	public SimulatorLoad_bSimulatorLoad bsimulatorload=new SimulatorLoad_bSimulatorLoad();
	@Sequence(1)
	public List<SimulatorLoad_bAgentList> bagentlist;
}
