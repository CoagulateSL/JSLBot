package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectDrop extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 114; }
	public final String getName() { return "ObjectDrop"; }
	@Sequence(0)
	public ObjectDrop_bAgentData bagentdata=new ObjectDrop_bAgentData();
	@Sequence(1)
	public List<ObjectDrop_bObjectData> bobjectdata;
	public ObjectDrop(){}
	public ObjectDrop(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
