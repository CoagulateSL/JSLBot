package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectDeGrab extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 119; }
	public final String getName() { return "ObjectDeGrab"; }
	@Sequence(0)
	public ObjectDeGrab_bAgentData bagentdata=new ObjectDeGrab_bAgentData();
	@Sequence(1)
	public ObjectDeGrab_bObjectData bobjectdata=new ObjectDeGrab_bObjectData();
	@Sequence(2)
	public List<ObjectDeGrab_bSurfaceInfo> bsurfaceinfo;
	public ObjectDeGrab(){}
	public ObjectDeGrab(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
