package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class DirEventsReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 37; }
	public final String getName() { return "DirEventsReply"; }
	@Sequence(0)
	public DirEventsReply_bAgentData bagentdata=new DirEventsReply_bAgentData();
	@Sequence(1)
	public DirEventsReply_bQueryData bquerydata=new DirEventsReply_bQueryData();
	@Sequence(2)
	public List<DirEventsReply_bQueryReplies> bqueryreplies;
	@Sequence(3)
	public List<DirEventsReply_bStatusData> bstatusdata;
	public DirEventsReply(){}
	public DirEventsReply(JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
