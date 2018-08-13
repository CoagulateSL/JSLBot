package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectDelink extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 116; }
	public final String getName() { return "ObjectDelink"; }
	@Sequence(0)
	public ObjectDelink_bAgentData bagentdata=new ObjectDelink_bAgentData();
	@Sequence(1)
	public List<ObjectDelink_bObjectData> bobjectdata;
	public ObjectDelink(){}
	public ObjectDelink(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
