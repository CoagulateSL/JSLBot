package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectSaleInfo extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 106; }
	public final String getName() { return "ObjectSaleInfo"; }
	@Sequence(0)
	public ObjectSaleInfo_bAgentData bagentdata=new ObjectSaleInfo_bAgentData();
	@Sequence(1)
	public List<ObjectSaleInfo_bObjectData> bobjectdata;
	public ObjectSaleInfo(){}
	public ObjectSaleInfo(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
