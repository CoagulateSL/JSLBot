package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class LiveHelpGroupReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 380; }
	public final String getName() { return "LiveHelpGroupReply"; }
	@Sequence(0)
	public LiveHelpGroupReply_bReplyData breplydata=new LiveHelpGroupReply_bReplyData();
}
