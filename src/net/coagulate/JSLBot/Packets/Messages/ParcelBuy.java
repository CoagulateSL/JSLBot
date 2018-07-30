package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ParcelBuy extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 213; }
	public final String getName() { return "ParcelBuy"; }
	@Sequence(0)
	public ParcelBuy_bAgentData bagentdata=new ParcelBuy_bAgentData();
	@Sequence(1)
	public ParcelBuy_bData bdata=new ParcelBuy_bData();
	@Sequence(2)
	public ParcelBuy_bParcelData bparceldata=new ParcelBuy_bParcelData();
}
