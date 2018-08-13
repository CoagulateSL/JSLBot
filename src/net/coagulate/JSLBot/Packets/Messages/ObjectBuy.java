package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectBuy extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 102; }
	public final String getName() { return "ObjectBuy"; }
	@Sequence(0)
	public ObjectBuy_bAgentData bagentdata=new ObjectBuy_bAgentData();
	@Sequence(1)
	public List<ObjectBuy_bObjectData> bobjectdata;
	public ObjectBuy(){}
	public ObjectBuy(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
