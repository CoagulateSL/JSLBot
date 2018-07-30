package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class DirLandReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 50; }
	public final String getName() { return "DirLandReply"; }
	@Sequence(0)
	public DirLandReply_bAgentData bagentdata=new DirLandReply_bAgentData();
	@Sequence(1)
	public DirLandReply_bQueryData bquerydata=new DirLandReply_bQueryData();
	@Sequence(2)
	public List<DirLandReply_bQueryReplies> bqueryreplies;
}
