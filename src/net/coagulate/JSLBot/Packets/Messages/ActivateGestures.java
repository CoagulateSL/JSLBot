package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ActivateGestures extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 316; }
	public final String getName() { return "ActivateGestures"; }
	@Sequence(0)
	public ActivateGestures_bAgentData bagentdata=new ActivateGestures_bAgentData();
	@Sequence(1)
	public List<ActivateGestures_bData> bdata;
}
