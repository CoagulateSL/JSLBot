package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class SendPostcard extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 412; }
	public final String getName() { return "SendPostcard"; }
	@Sequence(0)
	public SendPostcard_bAgentData bagentdata=new SendPostcard_bAgentData();
}
