package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class JoinGroupReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 344; }
	public final String getName() { return "JoinGroupReply"; }
	@Sequence(0)
	public JoinGroupReply_bAgentData bagentdata=new JoinGroupReply_bAgentData();
	@Sequence(1)
	public JoinGroupReply_bGroupData bgroupdata=new JoinGroupReply_bGroupData();
}
