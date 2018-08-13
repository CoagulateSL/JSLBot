package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectAttach extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 112; }
	public final String getName() { return "ObjectAttach"; }
	@Sequence(0)
	public ObjectAttach_bAgentData bagentdata=new ObjectAttach_bAgentData();
	@Sequence(1)
	public List<ObjectAttach_bObjectData> bobjectdata;
	public ObjectAttach(){}
	public ObjectAttach(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
