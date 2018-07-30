package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectDelete extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 89; }
	public final String getName() { return "ObjectDelete"; }
	@Sequence(0)
	public ObjectDelete_bAgentData bagentdata=new ObjectDelete_bAgentData();
	@Sequence(1)
	public List<ObjectDelete_bObjectData> bobjectdata;
}
