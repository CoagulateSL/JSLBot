package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectOwner extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 100; }
	public final String getName() { return "ObjectOwner"; }
	@Sequence(0)
	public ObjectOwner_bAgentData bagentdata=new ObjectOwner_bAgentData();
	@Sequence(1)
	public ObjectOwner_bHeaderData bheaderdata=new ObjectOwner_bHeaderData();
	@Sequence(2)
	public List<ObjectOwner_bObjectData> bobjectdata;
	public ObjectOwner(){}
	public ObjectOwner(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
