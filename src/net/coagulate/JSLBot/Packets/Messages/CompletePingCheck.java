package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class CompletePingCheck extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 2; }
	public final String getName() { return "CompletePingCheck"; }
	@Sequence(0)
	public CompletePingCheck_bPingID bpingid=new CompletePingCheck_bPingID();
}
