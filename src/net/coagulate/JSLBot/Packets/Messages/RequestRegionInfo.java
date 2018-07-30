package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RequestRegionInfo extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 141; }
	public final String getName() { return "RequestRegionInfo"; }
	@Sequence(0)
	public RequestRegionInfo_bAgentData bagentdata=new RequestRegionInfo_bAgentData();
}
