package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RegionHandshake extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 148; }
	public final String getName() { return "RegionHandshake"; }
	@Sequence(0)
	public RegionHandshake_bRegionInfo bregioninfo=new RegionHandshake_bRegionInfo();
	@Sequence(1)
	public RegionHandshake_bRegionInfo2 bregioninfo2=new RegionHandshake_bRegionInfo2();
	@Sequence(2)
	public RegionHandshake_bRegionInfo3 bregioninfo3=new RegionHandshake_bRegionInfo3();
	@Sequence(3)
	public List<RegionHandshake_bRegionInfo4> bregioninfo4;
}
