package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GroupTitlesReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 376; }
	public final String getName() { return "GroupTitlesReply"; }
	@Sequence(0)
	public GroupTitlesReply_bAgentData bagentdata=new GroupTitlesReply_bAgentData();
	@Sequence(1)
	public List<GroupTitlesReply_bGroupData> bgroupdata;
}
