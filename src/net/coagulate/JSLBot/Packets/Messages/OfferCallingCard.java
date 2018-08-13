package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class OfferCallingCard extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 301; }
	public final String getName() { return "OfferCallingCard"; }
	@Sequence(0)
	public OfferCallingCard_bAgentData bagentdata=new OfferCallingCard_bAgentData();
	@Sequence(1)
	public OfferCallingCard_bAgentBlock bagentblock=new OfferCallingCard_bAgentBlock();
	public OfferCallingCard(){}
	public OfferCallingCard(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
