package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectCategory extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 109; }
	public final String getName() { return "ObjectCategory"; }
	@Sequence(0)
	public ObjectCategory_bAgentData bagentdata=new ObjectCategory_bAgentData();
	@Sequence(1)
	public List<ObjectCategory_bObjectData> bobjectdata;
}
