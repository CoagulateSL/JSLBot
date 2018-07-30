package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class MuteListRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 262; }
	public final String getName() { return "MuteListRequest"; }
	@Sequence(0)
	public MuteListRequest_bAgentData bagentdata=new MuteListRequest_bAgentData();
	@Sequence(1)
	public MuteListRequest_bMuteData bmutedata=new MuteListRequest_bMuteData();
}
