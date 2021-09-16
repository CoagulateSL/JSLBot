package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ViewerStats extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 131; }
	@Nonnull
    public final String getName() { return "ViewerStats"; }
	@Nonnull
    @Sequence(0)
	public ViewerStats_bAgentData bagentdata=new ViewerStats_bAgentData();
	@Nonnull
    @Sequence(1)
	public ViewerStats_bDownloadTotals bdownloadtotals=new ViewerStats_bDownloadTotals();
	@Nonnull
    @Sequence(2)
	public ViewerStats_bNetStats bnetstats[]=new ViewerStats_bNetStats[2];
	@Nonnull
    @Sequence(3)
	public ViewerStats_bFailStats bfailstats=new ViewerStats_bFailStats();
	@Sequence(4)
	public List<ViewerStats_bMiscStats> bmiscstats;
	public ViewerStats(){}
	public ViewerStats(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
