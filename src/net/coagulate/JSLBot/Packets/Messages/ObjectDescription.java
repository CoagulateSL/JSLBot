package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectDescription extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 108; }
	public final String getName() { return "ObjectDescription"; }
	@Sequence(0)
	public ObjectDescription_bAgentData bagentdata=new ObjectDescription_bAgentData();
	@Sequence(1)
	public List<ObjectDescription_bObjectData> bobjectdata;
}
