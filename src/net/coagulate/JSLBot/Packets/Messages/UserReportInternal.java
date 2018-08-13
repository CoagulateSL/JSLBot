package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class UserReportInternal extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 21; }
	public final String getName() { return "UserReportInternal"; }
	@Sequence(0)
	public UserReportInternal_bReportData breportdata=new UserReportInternal_bReportData();
}
