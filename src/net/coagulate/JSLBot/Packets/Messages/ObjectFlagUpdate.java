package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectFlagUpdate extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 94; }
	public final String getName() { return "ObjectFlagUpdate"; }
	@Sequence(0)
	public ObjectFlagUpdate_bAgentData bagentdata=new ObjectFlagUpdate_bAgentData();
	@Sequence(1)
	public List<ObjectFlagUpdate_bExtraPhysics> bextraphysics;
	public ObjectFlagUpdate(){}
	public ObjectFlagUpdate(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
