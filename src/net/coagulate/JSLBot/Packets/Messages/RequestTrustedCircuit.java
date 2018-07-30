package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RequestTrustedCircuit extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 394; }
	public final String getName() { return "RequestTrustedCircuit"; }
}
