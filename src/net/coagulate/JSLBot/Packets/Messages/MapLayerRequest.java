package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class MapLayerRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 405; }
	@Nonnull
    public final String getName() { return "MapLayerRequest"; }
	@Nonnull
    @Sequence(0)
	public MapLayerRequest_bAgentData bagentdata=new MapLayerRequest_bAgentData();
	public MapLayerRequest(){}
	public MapLayerRequest(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
