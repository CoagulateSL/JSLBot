package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class NearestLandingRegionReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 145; }
	public final String getName() { return "NearestLandingRegionReply"; }
	@Sequence(0)
	public NearestLandingRegionReply_bLandingRegionData blandingregiondata=new NearestLandingRegionReply_bLandingRegionData();
}
