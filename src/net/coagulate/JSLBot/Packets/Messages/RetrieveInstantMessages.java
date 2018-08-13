package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RetrieveInstantMessages extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 255; }
	public final String getName() { return "RetrieveInstantMessages"; }
	@Sequence(0)
	public RetrieveInstantMessages_bAgentData bagentdata=new RetrieveInstantMessages_bAgentData();
	public RetrieveInstantMessages(){}
	public RetrieveInstantMessages(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
