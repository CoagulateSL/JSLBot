package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class CrossedRegion extends Block implements Message {
	public final int getFrequency() { return Frequency.MEDIUM; }
	public final int getId() { return 7; }
	@Nonnull
    public final String getName() { return "CrossedRegion"; }
	@Nonnull
    @Sequence(0)
	public CrossedRegion_bAgentData bagentdata=new CrossedRegion_bAgentData();
	@Nonnull
    @Sequence(1)
	public CrossedRegion_bRegionData bregiondata=new CrossedRegion_bRegionData();
	@Nonnull
    @Sequence(2)
	public CrossedRegion_bInfo binfo=new CrossedRegion_bInfo();
	public CrossedRegion(){}
	public CrossedRegion(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
