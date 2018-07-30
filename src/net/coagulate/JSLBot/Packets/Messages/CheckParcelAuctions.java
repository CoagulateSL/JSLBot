package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class CheckParcelAuctions extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 233; }
	public final String getName() { return "CheckParcelAuctions"; }
	@Sequence(0)
	public List<CheckParcelAuctions_bRegionData> bregiondata;
}
