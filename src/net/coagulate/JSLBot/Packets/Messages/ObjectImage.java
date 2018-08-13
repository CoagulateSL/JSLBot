package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectImage extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 96; }
	public final String getName() { return "ObjectImage"; }
	@Sequence(0)
	public ObjectImage_bAgentData bagentdata=new ObjectImage_bAgentData();
	@Sequence(1)
	public List<ObjectImage_bObjectData> bobjectdata;
	public ObjectImage(){}
	public ObjectImage(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
