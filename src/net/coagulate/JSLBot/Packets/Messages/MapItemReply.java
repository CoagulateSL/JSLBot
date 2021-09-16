package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class MapItemReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 411; }
	@Nonnull
    public final String getName() { return "MapItemReply"; }
	@Nonnull
    @Sequence(0)
	public MapItemReply_bAgentData bagentdata=new MapItemReply_bAgentData();
	@Nonnull
    @Sequence(1)
	public MapItemReply_bRequestData brequestdata=new MapItemReply_bRequestData();
	@Sequence(2)
	public List<MapItemReply_bData> bdata;
	public MapItemReply(){}
	public MapItemReply(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
