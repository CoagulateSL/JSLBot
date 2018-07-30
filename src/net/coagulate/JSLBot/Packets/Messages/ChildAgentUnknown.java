package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ChildAgentUnknown extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 241; }
	public final String getName() { return "ChildAgentUnknown"; }
	@Sequence(0)
	public ChildAgentUnknown_bAgentData bagentdata=new ChildAgentUnknown_bAgentData();
}
