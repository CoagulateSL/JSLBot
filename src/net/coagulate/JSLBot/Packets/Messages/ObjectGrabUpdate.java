package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectGrabUpdate extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 118; }
	public final String getName() { return "ObjectGrabUpdate"; }
	@Sequence(0)
	public ObjectGrabUpdate_bAgentData bagentdata=new ObjectGrabUpdate_bAgentData();
	@Sequence(1)
	public ObjectGrabUpdate_bObjectData bobjectdata=new ObjectGrabUpdate_bObjectData();
	@Sequence(2)
	public List<ObjectGrabUpdate_bSurfaceInfo> bsurfaceinfo;
	public ObjectGrabUpdate(){}
	public ObjectGrabUpdate(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
