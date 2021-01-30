package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RetrieveIMsExtended extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 427; }
	public final String getName() { return "RetrieveIMsExtended"; }
	@Sequence(0)
	public RetrieveIMsExtended_bAgentData bagentdata=new RetrieveIMsExtended_bAgentData();
	public RetrieveIMsExtended(){}
	public RetrieveIMsExtended(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
