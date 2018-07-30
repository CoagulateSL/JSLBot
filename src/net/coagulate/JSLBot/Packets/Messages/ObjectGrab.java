package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectGrab extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 117; }
	public final String getName() { return "ObjectGrab"; }
	@Sequence(0)
	public ObjectGrab_bAgentData bagentdata=new ObjectGrab_bAgentData();
	@Sequence(1)
	public ObjectGrab_bObjectData bobjectdata=new ObjectGrab_bObjectData();
	@Sequence(2)
	public List<ObjectGrab_bSurfaceInfo> bsurfaceinfo;
}
