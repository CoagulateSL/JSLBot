package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class PickInfoUpdate extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 185; }
	public final String getName() { return "PickInfoUpdate"; }
	@Sequence(0)
	public PickInfoUpdate_bAgentData bagentdata=new PickInfoUpdate_bAgentData();
	@Sequence(1)
	public PickInfoUpdate_bData bdata=new PickInfoUpdate_bData();
}
