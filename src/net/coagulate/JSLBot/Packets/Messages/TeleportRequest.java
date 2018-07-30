package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class TeleportRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 62; }
	public final String getName() { return "TeleportRequest"; }
	@Sequence(0)
	public TeleportRequest_bAgentData bagentdata=new TeleportRequest_bAgentData();
	@Sequence(1)
	public TeleportRequest_bInfo binfo=new TeleportRequest_bInfo();
}
