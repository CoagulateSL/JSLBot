package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class StartPingCheck extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 1; }
	public final String getName() { return "StartPingCheck"; }
	@Sequence(0)
	public StartPingCheck_bPingID bpingid=new StartPingCheck_bPingID();
}
