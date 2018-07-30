package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class SimulatorReady extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 9; }
	public final String getName() { return "SimulatorReady"; }
	@Sequence(0)
	public SimulatorReady_bSimulatorBlock bsimulatorblock=new SimulatorReady_bSimulatorBlock();
	@Sequence(1)
	public SimulatorReady_bTelehubBlock btelehubblock=new SimulatorReady_bTelehubBlock();
}
