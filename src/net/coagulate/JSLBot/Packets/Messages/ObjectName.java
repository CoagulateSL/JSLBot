package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectName extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 107; }
	public final String getName() { return "ObjectName"; }
	@Sequence(0)
	public ObjectName_bAgentData bagentdata=new ObjectName_bAgentData();
	@Sequence(1)
	public List<ObjectName_bObjectData> bobjectdata;
}
