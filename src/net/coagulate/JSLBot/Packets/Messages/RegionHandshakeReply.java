package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class RegionHandshakeReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 149; }
	@Nonnull
    public final String getName() { return "RegionHandshakeReply"; }
	@Nonnull
    @Sequence(0)
	public RegionHandshakeReply_bAgentData bagentdata=new RegionHandshakeReply_bAgentData();
	@Nonnull
    @Sequence(1)
	public RegionHandshakeReply_bRegionInfo bregioninfo=new RegionHandshakeReply_bRegionInfo();
	public RegionHandshakeReply(){}
	public RegionHandshakeReply(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
