package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class PayPriceReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 162; }
	public final String getName() { return "PayPriceReply"; }
	@Sequence(0)
	public PayPriceReply_bObjectData bobjectdata=new PayPriceReply_bObjectData();
	@Sequence(1)
	public List<PayPriceReply_bButtonData> bbuttondata;
}
