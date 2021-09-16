package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class MapItemRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 410; }
	@Nonnull
    public final String getName() { return "MapItemRequest"; }
	@Nonnull
    @Sequence(0)
	public MapItemRequest_bAgentData bagentdata=new MapItemRequest_bAgentData();
	@Nonnull
    @Sequence(1)
	public MapItemRequest_bRequestData brequestdata=new MapItemRequest_bRequestData();
	public MapItemRequest(){}
	public MapItemRequest(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
