package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectSelect extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 110; }
	public final String getName() { return "ObjectSelect"; }
	@Sequence(0)
	public ObjectSelect_bAgentData bagentdata=new ObjectSelect_bAgentData();
	@Sequence(1)
	public List<ObjectSelect_bObjectData> bobjectdata;
	public ObjectSelect(){}
	public ObjectSelect(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
