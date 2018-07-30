package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ChildAgentDying extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 240; }
	public final String getName() { return "ChildAgentDying"; }
	@Sequence(0)
	public ChildAgentDying_bAgentData bagentdata=new ChildAgentDying_bAgentData();
}
