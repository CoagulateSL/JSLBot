package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectScale extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 92; }
	public final String getName() { return "ObjectScale"; }
	@Sequence(0)
	public ObjectScale_bAgentData bagentdata=new ObjectScale_bAgentData();
	@Sequence(1)
	public List<ObjectScale_bObjectData> bobjectdata;
	public ObjectScale(){}
	public ObjectScale(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
