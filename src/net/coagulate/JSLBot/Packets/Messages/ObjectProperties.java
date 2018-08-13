package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectProperties extends Block implements Message {
	public final int getFrequency() { return Frequency.MEDIUM; }
	public final int getId() { return 9; }
	public final String getName() { return "ObjectProperties"; }
	@Sequence(0)
	public List<ObjectProperties_bObjectData> bobjectdata;
}
