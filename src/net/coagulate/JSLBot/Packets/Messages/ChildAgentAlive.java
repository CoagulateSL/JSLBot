package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ChildAgentAlive extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 26; }
	public final String getName() { return "ChildAgentAlive"; }
	@Sequence(0)
	public ChildAgentAlive_bAgentData bagentdata=new ChildAgentAlive_bAgentData();
}
