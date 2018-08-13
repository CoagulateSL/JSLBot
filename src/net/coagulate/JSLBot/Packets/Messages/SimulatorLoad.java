package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class SimulatorLoad extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 12; }
	public final String getName() { return "SimulatorLoad"; }
	@Sequence(0)
	public SimulatorLoad_bSimulatorLoad bsimulatorload=new SimulatorLoad_bSimulatorLoad();
	@Sequence(1)
	public List<SimulatorLoad_bAgentList> bagentlist;
}
