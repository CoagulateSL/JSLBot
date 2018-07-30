package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RegionPresenceResponse extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 16; }
	public final String getName() { return "RegionPresenceResponse"; }
	@Sequence(0)
	public List<RegionPresenceResponse_bRegionData> bregiondata;
}
