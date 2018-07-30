package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectPosition extends Block implements Message {
	public final int getFrequency() { return Frequency.MEDIUM; }
	public final int getId() { return 4; }
	public final String getName() { return "ObjectPosition"; }
	@Sequence(0)
	public ObjectPosition_bAgentData bagentdata=new ObjectPosition_bAgentData();
	@Sequence(1)
	public List<ObjectPosition_bObjectData> bobjectdata;
}
