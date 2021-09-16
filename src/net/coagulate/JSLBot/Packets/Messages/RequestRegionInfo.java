package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class RequestRegionInfo extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 141; }
	@Nonnull
    public final String getName() { return "RequestRegionInfo"; }
	@Nonnull
    @Sequence(0)
	public RequestRegionInfo_bAgentData bagentdata=new RequestRegionInfo_bAgentData();
	public RequestRegionInfo(){}
	public RequestRegionInfo(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
