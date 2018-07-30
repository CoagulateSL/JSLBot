package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class PickGodDelete extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 187; }
	public final String getName() { return "PickGodDelete"; }
	@Sequence(0)
	public PickGodDelete_bAgentData bagentdata=new PickGodDelete_bAgentData();
	@Sequence(1)
	public PickGodDelete_bData bdata=new PickGodDelete_bData();
}
