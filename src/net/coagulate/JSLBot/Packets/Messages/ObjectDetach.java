package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectDetach extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 113; }
	public final String getName() { return "ObjectDetach"; }
	@Sequence(0)
	public ObjectDetach_bAgentData bagentdata=new ObjectDetach_bAgentData();
	@Sequence(1)
	public List<ObjectDetach_bObjectData> bobjectdata;
	public ObjectDetach(){}
	public ObjectDetach(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
