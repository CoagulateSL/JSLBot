package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectIncludeInSearch extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 424; }
	public final String getName() { return "ObjectIncludeInSearch"; }
	@Sequence(0)
	public ObjectIncludeInSearch_bAgentData bagentdata=new ObjectIncludeInSearch_bAgentData();
	@Sequence(1)
	public List<ObjectIncludeInSearch_bObjectData> bobjectdata;
}
