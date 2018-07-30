package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectDeselect extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 111; }
	public final String getName() { return "ObjectDeselect"; }
	@Sequence(0)
	public ObjectDeselect_bAgentData bagentdata=new ObjectDeselect_bAgentData();
	@Sequence(1)
	public List<ObjectDeselect_bObjectData> bobjectdata;
}
