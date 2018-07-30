package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RequestPayPrice extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 161; }
	public final String getName() { return "RequestPayPrice"; }
	@Sequence(0)
	public RequestPayPrice_bObjectData bobjectdata=new RequestPayPrice_bObjectData();
}
