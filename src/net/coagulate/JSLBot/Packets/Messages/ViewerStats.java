package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ViewerStats extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 131; }
	public final String getName() { return "ViewerStats"; }
	@Sequence(0)
	public ViewerStats_bAgentData bagentdata=new ViewerStats_bAgentData();
	@Sequence(1)
	public ViewerStats_bDownloadTotals bdownloadtotals=new ViewerStats_bDownloadTotals();
	@Sequence(2)
	public ViewerStats_bNetStats bnetstats[]=new ViewerStats_bNetStats[2];
	@Sequence(3)
	public ViewerStats_bFailStats bfailstats=new ViewerStats_bFailStats();
	@Sequence(4)
	public List<ViewerStats_bMiscStats> bmiscstats;
}
