package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectAdd extends Block implements Message {
	public final int getFrequency() { return Frequency.MEDIUM; }
	public final int getId() { return 1; }
	public final String getName() { return "ObjectAdd"; }
	@Sequence(0)
	public ObjectAdd_bAgentData bagentdata=new ObjectAdd_bAgentData();
	@Sequence(1)
	public ObjectAdd_bObjectData bobjectdata=new ObjectAdd_bObjectData();
}
