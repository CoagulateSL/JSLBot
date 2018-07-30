package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RegionPresenceRequestByRegionID extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 14; }
	public final String getName() { return "RegionPresenceRequestByRegionID"; }
	@Sequence(0)
	public List<RegionPresenceRequestByRegionID_bRegionData> bregiondata;
}
