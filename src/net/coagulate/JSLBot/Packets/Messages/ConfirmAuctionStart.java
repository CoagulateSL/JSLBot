package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ConfirmAuctionStart extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 230; }
	public final String getName() { return "ConfirmAuctionStart"; }
	@Sequence(0)
	public ConfirmAuctionStart_bAuctionData bauctiondata=new ConfirmAuctionStart_bAuctionData();
}
