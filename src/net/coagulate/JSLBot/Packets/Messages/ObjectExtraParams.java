package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectExtraParams extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 99; }
	public final String getName() { return "ObjectExtraParams"; }
	@Sequence(0)
	public ObjectExtraParams_bAgentData bagentdata=new ObjectExtraParams_bAgentData();
	@Sequence(1)
	public List<ObjectExtraParams_bObjectData> bobjectdata;
	public ObjectExtraParams(){}
	public ObjectExtraParams(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
