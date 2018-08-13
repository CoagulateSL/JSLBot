package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class DirPopularReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 53; }
	public final String getName() { return "DirPopularReply"; }
	@Sequence(0)
	public DirPopularReply_bAgentData bagentdata=new DirPopularReply_bAgentData();
	@Sequence(1)
	public DirPopularReply_bQueryData bquerydata=new DirPopularReply_bQueryData();
	@Sequence(2)
	public List<DirPopularReply_bQueryReplies> bqueryreplies;
	public DirPopularReply(){}
	public DirPopularReply(JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
