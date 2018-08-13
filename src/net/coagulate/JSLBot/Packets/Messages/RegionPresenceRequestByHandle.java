package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RegionPresenceRequestByHandle extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 15; }
	public final String getName() { return "RegionPresenceRequestByHandle"; }
	@Sequence(0)
	public List<RegionPresenceRequestByHandle_bRegionData> bregiondata;
}
