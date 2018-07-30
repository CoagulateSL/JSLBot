package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectRotation extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 93; }
	public final String getName() { return "ObjectRotation"; }
	@Sequence(0)
	public ObjectRotation_bAgentData bagentdata=new ObjectRotation_bAgentData();
	@Sequence(1)
	public List<ObjectRotation_bObjectData> bobjectdata;
}
