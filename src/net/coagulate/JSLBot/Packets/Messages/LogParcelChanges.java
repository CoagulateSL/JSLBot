package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class LogParcelChanges extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 224; }
	public final String getName() { return "LogParcelChanges"; }
	@Sequence(0)
	public LogParcelChanges_bAgentData bagentdata=new LogParcelChanges_bAgentData();
	@Sequence(1)
	public LogParcelChanges_bRegionData bregiondata=new LogParcelChanges_bRegionData();
	@Sequence(2)
	public List<LogParcelChanges_bParcelData> bparceldata;
}
