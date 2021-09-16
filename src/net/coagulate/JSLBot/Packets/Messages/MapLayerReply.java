package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class MapLayerReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 406; }
	@Nonnull
    public final String getName() { return "MapLayerReply"; }
	@Nonnull
    @Sequence(0)
	public MapLayerReply_bAgentData bagentdata=new MapLayerReply_bAgentData();
	@Sequence(1)
	public List<MapLayerReply_bLayerData> blayerdata;
	public MapLayerReply(){}
	public MapLayerReply(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
