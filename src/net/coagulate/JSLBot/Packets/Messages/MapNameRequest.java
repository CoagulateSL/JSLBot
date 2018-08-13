package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class MapNameRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 408; }
	public final String getName() { return "MapNameRequest"; }
	@Sequence(0)
	public MapNameRequest_bAgentData bagentdata=new MapNameRequest_bAgentData();
	@Sequence(1)
	public MapNameRequest_bNameData bnamedata=new MapNameRequest_bNameData();
	public MapNameRequest(){}
	public MapNameRequest(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
