package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class LogParcelChanges extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 224; }
	@Nonnull
    public final String getName() { return "LogParcelChanges"; }
	@Nonnull
    @Sequence(0)
	public LogParcelChanges_bAgentData bagentdata=new LogParcelChanges_bAgentData();
	@Nonnull
    @Sequence(1)
	public LogParcelChanges_bRegionData bregiondata=new LogParcelChanges_bRegionData();
	@Sequence(2)
	public List<LogParcelChanges_bParcelData> bparceldata;
	public LogParcelChanges(){}
	public LogParcelChanges(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
