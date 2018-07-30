package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GroupNoticesListRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 58; }
	public final String getName() { return "GroupNoticesListRequest"; }
	@Sequence(0)
	public GroupNoticesListRequest_bAgentData bagentdata=new GroupNoticesListRequest_bAgentData();
	@Sequence(1)
	public GroupNoticesListRequest_bData bdata=new GroupNoticesListRequest_bData();
}
