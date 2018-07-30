package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class KillObject extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 16; }
	public final String getName() { return "KillObject"; }
	@Sequence(0)
	public List<KillObject_bObjectData> bobjectdata;
}
