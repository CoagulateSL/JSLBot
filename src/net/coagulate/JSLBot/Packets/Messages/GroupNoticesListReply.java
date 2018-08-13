package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GroupNoticesListReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 59; }
	public final String getName() { return "GroupNoticesListReply"; }
	@Sequence(0)
	public GroupNoticesListReply_bAgentData bagentdata=new GroupNoticesListReply_bAgentData();
	@Sequence(1)
	public List<GroupNoticesListReply_bData> bdata;
	public GroupNoticesListReply(){}
	public GroupNoticesListReply(JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
