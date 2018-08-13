package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class DirPlacesReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 35; }
	public final String getName() { return "DirPlacesReply"; }
	@Sequence(0)
	public DirPlacesReply_bAgentData bagentdata=new DirPlacesReply_bAgentData();
	@Sequence(1)
	public List<DirPlacesReply_bQueryData> bquerydata;
	@Sequence(2)
	public List<DirPlacesReply_bQueryReplies> bqueryreplies;
	@Sequence(3)
	public List<DirPlacesReply_bStatusData> bstatusdata;
	public DirPlacesReply(){}
	public DirPlacesReply(JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
