package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class LandStatRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 421; }
	public final String getName() { return "LandStatRequest"; }
	@Sequence(0)
	public LandStatRequest_bAgentData bagentdata=new LandStatRequest_bAgentData();
	@Sequence(1)
	public LandStatRequest_bRequestData brequestdata=new LandStatRequest_bRequestData();
	public LandStatRequest(){}
	public LandStatRequest(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
