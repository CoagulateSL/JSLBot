package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectSpinUpdate extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 121; }
	public final String getName() { return "ObjectSpinUpdate"; }
	@Sequence(0)
	public ObjectSpinUpdate_bAgentData bagentdata=new ObjectSpinUpdate_bAgentData();
	@Sequence(1)
	public ObjectSpinUpdate_bObjectData bobjectdata=new ObjectSpinUpdate_bObjectData();
	public ObjectSpinUpdate(){}
	public ObjectSpinUpdate(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
