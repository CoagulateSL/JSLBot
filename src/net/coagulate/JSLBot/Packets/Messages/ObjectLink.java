package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectLink extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 115; }
	public final String getName() { return "ObjectLink"; }
	@Sequence(0)
	public ObjectLink_bAgentData bagentdata=new ObjectLink_bAgentData();
	@Sequence(1)
	public List<ObjectLink_bObjectData> bobjectdata;
	public ObjectLink(){}
	public ObjectLink(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
