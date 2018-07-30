package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ParcelBuyPass extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 206; }
	public final String getName() { return "ParcelBuyPass"; }
	@Sequence(0)
	public ParcelBuyPass_bAgentData bagentdata=new ParcelBuyPass_bAgentData();
	@Sequence(1)
	public ParcelBuyPass_bParcelData bparceldata=new ParcelBuyPass_bParcelData();
}
