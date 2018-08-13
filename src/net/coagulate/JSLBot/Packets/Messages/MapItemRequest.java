package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class MapItemRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 410; }
	public final String getName() { return "MapItemRequest"; }
	@Sequence(0)
	public MapItemRequest_bAgentData bagentdata=new MapItemRequest_bAgentData();
	@Sequence(1)
	public MapItemRequest_bRequestData brequestdata=new MapItemRequest_bRequestData();
	public MapItemRequest(){}
	public MapItemRequest(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
