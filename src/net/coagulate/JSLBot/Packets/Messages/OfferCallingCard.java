package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
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
}
