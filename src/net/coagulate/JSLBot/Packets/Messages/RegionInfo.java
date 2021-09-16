package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class RegionInfo extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 142; }
	@Nonnull
    public final String getName() { return "RegionInfo"; }
	@Nonnull
    @Sequence(0)
	public RegionInfo_bAgentData bagentdata=new RegionInfo_bAgentData();
	@Nonnull
    @Sequence(1)
	public RegionInfo_bRegionInfo bregioninfo=new RegionInfo_bRegionInfo();
	@Nonnull
    @Sequence(2)
	public RegionInfo_bRegionInfo2 bregioninfo2=new RegionInfo_bRegionInfo2();
	@Sequence(3)
	public List<RegionInfo_bRegionInfo3> bregioninfo3;
	public RegionInfo(){}
	public RegionInfo(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
