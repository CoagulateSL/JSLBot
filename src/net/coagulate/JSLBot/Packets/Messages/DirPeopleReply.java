package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class DirPeopleReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 36; }
	public final String getName() { return "DirPeopleReply"; }
	@Sequence(0)
	public DirPeopleReply_bAgentData bagentdata=new DirPeopleReply_bAgentData();
	@Sequence(1)
	public DirPeopleReply_bQueryData bquerydata=new DirPeopleReply_bQueryData();
	@Sequence(2)
	public List<DirPeopleReply_bQueryReplies> bqueryreplies;
}
