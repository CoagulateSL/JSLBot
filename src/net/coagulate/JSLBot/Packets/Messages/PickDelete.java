package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class PickDelete extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 186; }
	public final String getName() { return "PickDelete"; }
	@Sequence(0)
	public PickDelete_bAgentData bagentdata=new PickDelete_bAgentData();
	@Sequence(1)
	public PickDelete_bData bdata=new PickDelete_bData();
}
