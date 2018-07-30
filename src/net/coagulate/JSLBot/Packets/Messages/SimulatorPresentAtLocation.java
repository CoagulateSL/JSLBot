package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class SimulatorPresentAtLocation extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 11; }
	public final String getName() { return "SimulatorPresentAtLocation"; }
	@Sequence(0)
	public SimulatorPresentAtLocation_bSimulatorPublicHostBlock bsimulatorpublichostblock=new SimulatorPresentAtLocation_bSimulatorPublicHostBlock();
	@Sequence(1)
	public SimulatorPresentAtLocation_bNeighborBlock bneighborblock[]=new SimulatorPresentAtLocation_bNeighborBlock[4];
	@Sequence(2)
	public SimulatorPresentAtLocation_bSimulatorBlock bsimulatorblock=new SimulatorPresentAtLocation_bSimulatorBlock();
	@Sequence(3)
	public List<SimulatorPresentAtLocation_bTelehubBlock> btelehubblock;
}
