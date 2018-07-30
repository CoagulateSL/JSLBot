package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class TeleportLocationRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 63; }
	public final String getName() { return "TeleportLocationRequest"; }
	@Sequence(0)
	public TeleportLocationRequest_bAgentData bagentdata=new TeleportLocationRequest_bAgentData();
	@Sequence(1)
	public TeleportLocationRequest_bInfo binfo=new TeleportLocationRequest_bInfo();
}
