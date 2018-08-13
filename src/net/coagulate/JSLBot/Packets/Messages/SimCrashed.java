package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class SimCrashed extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 328; }
	public final String getName() { return "SimCrashed"; }
	@Sequence(0)
	public SimCrashed_bData bdata=new SimCrashed_bData();
	@Sequence(1)
	public List<SimCrashed_bUsers> busers;
}
