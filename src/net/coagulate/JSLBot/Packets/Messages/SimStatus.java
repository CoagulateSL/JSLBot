package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class SimStatus extends Block implements Message {
	public final int getFrequency() { return Frequency.MEDIUM; }
	public final int getId() { return 12; }
	public final String getName() { return "SimStatus"; }
	@Sequence(0)
	public SimStatus_bSimStatus bsimstatus=new SimStatus_bSimStatus();
}
