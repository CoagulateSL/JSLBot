package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectGroup extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 101; }
	public final String getName() { return "ObjectGroup"; }
	@Sequence(0)
	public ObjectGroup_bAgentData bagentdata=new ObjectGroup_bAgentData();
	@Sequence(1)
	public List<ObjectGroup_bObjectData> bobjectdata;
}
