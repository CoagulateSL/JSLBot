package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class OpenCircuit extends Block implements Message {
	public final int getFrequency() { return Frequency.FIXED; }
	public final int getId() { return 0xFFFFFFFC; }
	public final String getName() { return "OpenCircuit"; }
	@Sequence(0)
	public OpenCircuit_bCircuitInfo bcircuitinfo=new OpenCircuit_bCircuitInfo();
}
