package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class PayPriceReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 162; }
	@Nonnull
    public final String getName() { return "PayPriceReply"; }
	@Nonnull
    @Sequence(0)
	public PayPriceReply_bObjectData bobjectdata=new PayPriceReply_bObjectData();
	@Sequence(1)
	public List<PayPriceReply_bButtonData> bbuttondata;
}
