package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class LogoutRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 252; }
	public final String getName() { return "LogoutRequest"; }
	@Sequence(0)
	public LogoutRequest_bAgentData bagentdata=new LogoutRequest_bAgentData();
}
