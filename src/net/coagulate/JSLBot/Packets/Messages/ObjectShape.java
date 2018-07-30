package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectShape extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 98; }
	public final String getName() { return "ObjectShape"; }
	@Sequence(0)
	public ObjectShape_bAgentData bagentdata=new ObjectShape_bAgentData();
	@Sequence(1)
	public List<ObjectShape_bObjectData> bobjectdata;
}
