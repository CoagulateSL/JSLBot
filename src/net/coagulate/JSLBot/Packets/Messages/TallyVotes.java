package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class TallyVotes extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 365; }
	public final String getName() { return "TallyVotes"; }
}
