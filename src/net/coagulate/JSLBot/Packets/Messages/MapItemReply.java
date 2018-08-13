package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class MapItemReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 411; }
	public final String getName() { return "MapItemReply"; }
	@Sequence(0)
	public MapItemReply_bAgentData bagentdata=new MapItemReply_bAgentData();
	@Sequence(1)
	public MapItemReply_bRequestData brequestdata=new MapItemReply_bRequestData();
	@Sequence(2)
	public List<MapItemReply_bData> bdata;
	public MapItemReply(){}
	public MapItemReply(JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
