package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class NearestLandingRegionUpdated extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 146; }
	public final String getName() { return "NearestLandingRegionUpdated"; }
	@Sequence(0)
	public NearestLandingRegionUpdated_bRegionData bregiondata=new NearestLandingRegionUpdated_bRegionData();
}
