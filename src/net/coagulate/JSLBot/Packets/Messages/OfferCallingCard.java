package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class OfferCallingCard extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 301; }
	@Nonnull
    public final String getName() { return "OfferCallingCard"; }
	@Nonnull
    @Sequence(0)
	public OfferCallingCard_bAgentData bagentdata=new OfferCallingCard_bAgentData();
	@Nonnull
    @Sequence(1)
	public OfferCallingCard_bAgentBlock bagentblock=new OfferCallingCard_bAgentBlock();
	public OfferCallingCard(){}
	public OfferCallingCard(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
