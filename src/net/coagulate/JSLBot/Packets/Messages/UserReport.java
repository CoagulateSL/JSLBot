package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class UserReport extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 133; }
	public final String getName() { return "UserReport"; }
	@Sequence(0)
	public UserReport_bAgentData bagentdata=new UserReport_bAgentData();
	@Sequence(1)
	public UserReport_bReportData breportdata=new UserReport_bReportData();
}
