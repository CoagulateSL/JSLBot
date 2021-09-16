package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class GodUpdateRegionInfo extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 143; }
	@Nonnull
    public final String getName() { return "GodUpdateRegionInfo"; }
	@Nonnull
    @Sequence(0)
	public GodUpdateRegionInfo_bAgentData bagentdata=new GodUpdateRegionInfo_bAgentData();
	@Nonnull
    @Sequence(1)
	public GodUpdateRegionInfo_bRegionInfo bregioninfo=new GodUpdateRegionInfo_bRegionInfo();
	@Sequence(2)
	public List<GodUpdateRegionInfo_bRegionInfo2> bregioninfo2;
	public GodUpdateRegionInfo(){}
	public GodUpdateRegionInfo(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
