package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class SetStartLocationRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 324; }
	public final String getName() { return "SetStartLocationRequest"; }
	@Sequence(0)
	public SetStartLocationRequest_bAgentData bagentdata=new SetStartLocationRequest_bAgentData();
	@Sequence(1)
	public SetStartLocationRequest_bStartLocationData bstartlocationdata=new SetStartLocationRequest_bStartLocationData();
	public SetStartLocationRequest(){}
	public SetStartLocationRequest(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
