package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class SimulatorSetMap extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 6; }
	public final String getName() { return "SimulatorSetMap"; }
	@Sequence(0)
	public SimulatorSetMap_bMapData bmapdata=new SimulatorSetMap_bMapData();
}
