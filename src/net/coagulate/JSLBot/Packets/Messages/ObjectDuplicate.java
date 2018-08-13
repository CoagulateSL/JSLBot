package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectDuplicate extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 90; }
	public final String getName() { return "ObjectDuplicate"; }
	@Sequence(0)
	public ObjectDuplicate_bAgentData bagentdata=new ObjectDuplicate_bAgentData();
	@Sequence(1)
	public ObjectDuplicate_bSharedData bshareddata=new ObjectDuplicate_bSharedData();
	@Sequence(2)
	public List<ObjectDuplicate_bObjectData> bobjectdata;
	public ObjectDuplicate(){}
	public ObjectDuplicate(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
