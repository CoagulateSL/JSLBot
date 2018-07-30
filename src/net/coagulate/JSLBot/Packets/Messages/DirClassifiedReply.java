package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class DirClassifiedReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 41; }
	public final String getName() { return "DirClassifiedReply"; }
	@Sequence(0)
	public DirClassifiedReply_bAgentData bagentdata=new DirClassifiedReply_bAgentData();
	@Sequence(1)
	public DirClassifiedReply_bQueryData bquerydata=new DirClassifiedReply_bQueryData();
	@Sequence(2)
	public List<DirClassifiedReply_bQueryReplies> bqueryreplies;
	@Sequence(3)
	public List<DirClassifiedReply_bStatusData> bstatusdata;
}
