package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class LoadURL extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 194; }
	public final String getName() { return "LoadURL"; }
	@Sequence(0)
	public LoadURL_bData bdata=new LoadURL_bData();
}
