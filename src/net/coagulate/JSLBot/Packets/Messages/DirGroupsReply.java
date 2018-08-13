package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class DirGroupsReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 38; }
	public final String getName() { return "DirGroupsReply"; }
	@Sequence(0)
	public DirGroupsReply_bAgentData bagentdata=new DirGroupsReply_bAgentData();
	@Sequence(1)
	public DirGroupsReply_bQueryData bquerydata=new DirGroupsReply_bQueryData();
	@Sequence(2)
	public List<DirGroupsReply_bQueryReplies> bqueryreplies;
	public DirGroupsReply(){}
	public DirGroupsReply(JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
