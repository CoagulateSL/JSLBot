package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class SimulatorMapUpdate extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 5; }
	public final String getName() { return "SimulatorMapUpdate"; }
	@Sequence(0)
	public SimulatorMapUpdate_bMapData bmapdata=new SimulatorMapUpdate_bMapData();
}
