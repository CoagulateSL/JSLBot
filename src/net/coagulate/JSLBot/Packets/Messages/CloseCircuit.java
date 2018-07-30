package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class CloseCircuit extends Block implements Message {
	public final int getFrequency() { return Frequency.FIXED; }
	public final int getId() { return 0xFFFFFFFD; }
	public final String getName() { return "CloseCircuit"; }
}
