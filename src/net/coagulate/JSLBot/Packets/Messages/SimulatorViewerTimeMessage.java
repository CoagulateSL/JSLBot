package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class SimulatorViewerTimeMessage extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 150; }
	public final String getName() { return "SimulatorViewerTimeMessage"; }
	@Sequence(0)
	public SimulatorViewerTimeMessage_bTimeInfo btimeinfo=new SimulatorViewerTimeMessage_bTimeInfo();
}
