package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class DeactivateGestures extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 317; }
	public final String getName() { return "DeactivateGestures"; }
	@Sequence(0)
	public DeactivateGestures_bAgentData bagentdata=new DeactivateGestures_bAgentData();
	@Sequence(1)
	public List<DeactivateGestures_bData> bdata;
}
