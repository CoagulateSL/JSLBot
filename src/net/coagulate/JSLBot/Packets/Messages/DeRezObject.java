package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class DeRezObject extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 291; }
	public final String getName() { return "DeRezObject"; }
	@Sequence(0)
	public DeRezObject_bAgentData bagentdata=new DeRezObject_bAgentData();
	@Sequence(1)
	public DeRezObject_bAgentBlock bagentblock=new DeRezObject_bAgentBlock();
	@Sequence(2)
	public List<DeRezObject_bObjectData> bobjectdata;
}
