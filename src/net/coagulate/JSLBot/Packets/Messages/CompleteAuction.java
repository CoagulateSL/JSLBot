package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class CompleteAuction extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 231; }
	public final String getName() { return "CompleteAuction"; }
	@Sequence(0)
	public List<CompleteAuction_bParcelData> bparceldata;
}
