package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RemoveParcel extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 222; }
	public final String getName() { return "RemoveParcel"; }
	@Sequence(0)
	public List<RemoveParcel_bParcelData> bparceldata;
}
