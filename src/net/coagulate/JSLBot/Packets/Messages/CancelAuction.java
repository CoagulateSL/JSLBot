package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class CancelAuction extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 232; }
	public final String getName() { return "CancelAuction"; }
	@Sequence(0)
	public List<CancelAuction_bParcelData> bparceldata;
}
