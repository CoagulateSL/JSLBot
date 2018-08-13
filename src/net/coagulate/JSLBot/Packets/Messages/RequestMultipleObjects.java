package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RequestMultipleObjects extends Block implements Message {
	public final int getFrequency() { return Frequency.MEDIUM; }
	public final int getId() { return 3; }
	public final String getName() { return "RequestMultipleObjects"; }
	@Sequence(0)
	public RequestMultipleObjects_bAgentData bagentdata=new RequestMultipleObjects_bAgentData();
	@Sequence(1)
	public List<RequestMultipleObjects_bObjectData> bobjectdata;
	public RequestMultipleObjects(){}
	public RequestMultipleObjects(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
