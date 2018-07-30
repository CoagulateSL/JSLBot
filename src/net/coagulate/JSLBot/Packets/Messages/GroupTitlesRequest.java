package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GroupTitlesRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 375; }
	public final String getName() { return "GroupTitlesRequest"; }
	@Sequence(0)
	public GroupTitlesRequest_bAgentData bagentdata=new GroupTitlesRequest_bAgentData();
}
