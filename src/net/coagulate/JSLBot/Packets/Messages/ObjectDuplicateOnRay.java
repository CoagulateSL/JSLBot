package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectDuplicateOnRay extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 91; }
	public final String getName() { return "ObjectDuplicateOnRay"; }
	@Sequence(0)
	public ObjectDuplicateOnRay_bAgentData bagentdata=new ObjectDuplicateOnRay_bAgentData();
	@Sequence(1)
	public List<ObjectDuplicateOnRay_bObjectData> bobjectdata;
	public ObjectDuplicateOnRay(){}
	public ObjectDuplicateOnRay(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
