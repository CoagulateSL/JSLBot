package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class SimStats extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 140; }
	public final String getName() { return "SimStats"; }
	@Sequence(0)
	public SimStats_bRegion bregion=new SimStats_bRegion();
	@Sequence(1)
	public List<SimStats_bStat> bstat;
	@Sequence(2)
	public SimStats_bPidStat bpidstat=new SimStats_bPidStat();
}
