package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectSpinStart extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 120; }
	public final String getName() { return "ObjectSpinStart"; }
	@Sequence(0)
	public ObjectSpinStart_bAgentData bagentdata=new ObjectSpinStart_bAgentData();
	@Sequence(1)
	public ObjectSpinStart_bObjectData bobjectdata=new ObjectSpinStart_bObjectData();
}
