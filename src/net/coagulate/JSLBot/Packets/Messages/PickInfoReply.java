package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class PickInfoReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 184; }
	public final String getName() { return "PickInfoReply"; }
	@Sequence(0)
	public PickInfoReply_bAgentData bagentdata=new PickInfoReply_bAgentData();
	@Sequence(1)
	public PickInfoReply_bData bdata=new PickInfoReply_bData();
}
