package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class FindAgent extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 256; }
	public final String getName() { return "FindAgent"; }
	@Sequence(0)
	public FindAgent_bAgentBlock bagentblock=new FindAgent_bAgentBlock();
	@Sequence(1)
	public List<FindAgent_bLocationBlock> blocationblock;
}
