package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ParcelOverlay extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 196; }
	public final String getName() { return "ParcelOverlay"; }
	@Sequence(0)
	public ParcelOverlay_bParcelData bparceldata=new ParcelOverlay_bParcelData();
}
