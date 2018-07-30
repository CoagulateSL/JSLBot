package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ParcelGodMarkAsContent extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 227; }
	public final String getName() { return "ParcelGodMarkAsContent"; }
	@Sequence(0)
	public ParcelGodMarkAsContent_bAgentData bagentdata=new ParcelGodMarkAsContent_bAgentData();
	@Sequence(1)
	public ParcelGodMarkAsContent_bParcelData bparceldata=new ParcelGodMarkAsContent_bParcelData();
}
