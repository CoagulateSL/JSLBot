package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class RegionHandshake extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 148; }
	@Nonnull
    public final String getName() { return "RegionHandshake"; }
	@Nonnull
    @Sequence(0)
	public RegionHandshake_bRegionInfo bregioninfo=new RegionHandshake_bRegionInfo();
	@Nonnull
    @Sequence(1)
	public RegionHandshake_bRegionInfo2 bregioninfo2=new RegionHandshake_bRegionInfo2();
	@Nonnull
    @Sequence(2)
	public RegionHandshake_bRegionInfo3 bregioninfo3=new RegionHandshake_bRegionInfo3();
	@Sequence(3)
	public List<RegionHandshake_bRegionInfo4> bregioninfo4;
}
