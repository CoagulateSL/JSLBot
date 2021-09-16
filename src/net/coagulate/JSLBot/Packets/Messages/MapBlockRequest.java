package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class MapBlockRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 407; }
	@Nonnull
    public final String getName() { return "MapBlockRequest"; }
	@Nonnull
    @Sequence(0)
	public MapBlockRequest_bAgentData bagentdata=new MapBlockRequest_bAgentData();
	@Nonnull
    @Sequence(1)
	public MapBlockRequest_bPositionData bpositiondata=new MapBlockRequest_bPositionData();
	public MapBlockRequest(){}
	public MapBlockRequest(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
