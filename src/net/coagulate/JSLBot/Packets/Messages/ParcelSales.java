package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ParcelSales extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 226; }
	public final String getName() { return "ParcelSales"; }
	@Sequence(0)
	public List<ParcelSales_bParcelData> bparceldata;
}
