package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AvatarPicksReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 178; }
	public final String getName() { return "AvatarPicksReply"; }
	@Sequence(0)
	public AvatarPicksReply_bAgentData bagentdata=new AvatarPicksReply_bAgentData();
	@Sequence(1)
	public List<AvatarPicksReply_bData> bdata;
}
