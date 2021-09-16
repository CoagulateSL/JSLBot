package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class MapBlockReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 409; }
	@Nonnull
    public final String getName() { return "MapBlockReply"; }
	@Nonnull
    @Sequence(0)
	public MapBlockReply_bAgentData bagentdata=new MapBlockReply_bAgentData();
	@Sequence(1)
	public List<MapBlockReply_bData> bdata;
	public MapBlockReply(){}
	public MapBlockReply(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
