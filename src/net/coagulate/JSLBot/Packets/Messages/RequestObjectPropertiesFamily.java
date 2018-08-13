package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RequestObjectPropertiesFamily extends Block implements Message {
	public final int getFrequency() { return Frequency.MEDIUM; }
	public final int getId() { return 5; }
	public final String getName() { return "RequestObjectPropertiesFamily"; }
	@Sequence(0)
	public RequestObjectPropertiesFamily_bAgentData bagentdata=new RequestObjectPropertiesFamily_bAgentData();
	@Sequence(1)
	public RequestObjectPropertiesFamily_bObjectData bobjectdata=new RequestObjectPropertiesFamily_bObjectData();
	public RequestObjectPropertiesFamily(){}
	public RequestObjectPropertiesFamily(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
