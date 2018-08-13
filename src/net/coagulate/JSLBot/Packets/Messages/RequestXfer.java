package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RequestXfer extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 156; }
	public final String getName() { return "RequestXfer"; }
	@Sequence(0)
	public RequestXfer_bXferID bxferid=new RequestXfer_bXferID();
}
