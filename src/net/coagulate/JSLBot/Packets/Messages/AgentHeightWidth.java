package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AgentHeightWidth extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 83; }
	public final String getName() { return "AgentHeightWidth"; }
	@Sequence(0)
	public AgentHeightWidth_bAgentData bagentdata=new AgentHeightWidth_bAgentData();
	@Sequence(1)
	public AgentHeightWidth_bHeightWidthBlock bheightwidthblock=new AgentHeightWidth_bHeightWidthBlock();
}
