package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class NearestLandingRegionReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 145; }
	@Nonnull
    public final String getName() { return "NearestLandingRegionReply"; }
	@Nonnull
    @Sequence(0)
	public NearestLandingRegionReply_bLandingRegionData blandingregiondata=new NearestLandingRegionReply_bLandingRegionData();
}
