package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectClickAction extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 95; }
	public final String getName() { return "ObjectClickAction"; }
	@Sequence(0)
	public ObjectClickAction_bAgentData bagentdata=new ObjectClickAction_bAgentData();
	@Sequence(1)
	public List<ObjectClickAction_bObjectData> bobjectdata;
	public ObjectClickAction(){}
	public ObjectClickAction(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
