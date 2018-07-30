package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class LiveHelpGroupRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 379; }
	public final String getName() { return "LiveHelpGroupRequest"; }
	@Sequence(0)
	public LiveHelpGroupRequest_bRequestData brequestdata=new LiveHelpGroupRequest_bRequestData();
}
