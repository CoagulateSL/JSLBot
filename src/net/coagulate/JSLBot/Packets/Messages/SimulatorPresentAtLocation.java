package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class SimulatorPresentAtLocation extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 11; }
	@Nonnull
    public final String getName() { return "SimulatorPresentAtLocation"; }
	@Nonnull
    @Sequence(0)
	public SimulatorPresentAtLocation_bSimulatorPublicHostBlock bsimulatorpublichostblock=new SimulatorPresentAtLocation_bSimulatorPublicHostBlock();
	@Nonnull
    @Sequence(1)
	public SimulatorPresentAtLocation_bNeighborBlock bneighborblock[]=new SimulatorPresentAtLocation_bNeighborBlock[4];
	@Nonnull
    @Sequence(2)
	public SimulatorPresentAtLocation_bSimulatorBlock bsimulatorblock=new SimulatorPresentAtLocation_bSimulatorBlock();
	@Sequence(3)
	public List<SimulatorPresentAtLocation_bTelehubBlock> btelehubblock;
}
