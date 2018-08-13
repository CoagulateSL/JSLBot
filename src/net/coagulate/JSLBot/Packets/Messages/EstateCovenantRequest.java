package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class EstateCovenantRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 203; }
	public final String getName() { return "EstateCovenantRequest"; }
	@Sequence(0)
	public EstateCovenantRequest_bAgentData bagentdata=new EstateCovenantRequest_bAgentData();
	public EstateCovenantRequest(){}
	public EstateCovenantRequest(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
