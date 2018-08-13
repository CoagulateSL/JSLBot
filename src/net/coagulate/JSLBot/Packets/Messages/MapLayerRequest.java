package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class MapLayerRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 405; }
	public final String getName() { return "MapLayerRequest"; }
	@Sequence(0)
	public MapLayerRequest_bAgentData bagentdata=new MapLayerRequest_bAgentData();
	public MapLayerRequest(){}
	public MapLayerRequest(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
