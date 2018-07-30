package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class CreateGroupReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 340; }
	public final String getName() { return "CreateGroupReply"; }
	@Sequence(0)
	public CreateGroupReply_bAgentData bagentdata=new CreateGroupReply_bAgentData();
	@Sequence(1)
	public CreateGroupReply_bReplyData breplydata=new CreateGroupReply_bReplyData();
}
