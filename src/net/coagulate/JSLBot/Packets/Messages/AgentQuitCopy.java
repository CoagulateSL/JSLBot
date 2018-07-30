package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AgentQuitCopy extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 85; }
	public final String getName() { return "AgentQuitCopy"; }
	@Sequence(0)
	public AgentQuitCopy_bAgentData bagentdata=new AgentQuitCopy_bAgentData();
	@Sequence(1)
	public AgentQuitCopy_bFuseBlock bfuseblock=new AgentQuitCopy_bFuseBlock();
}
