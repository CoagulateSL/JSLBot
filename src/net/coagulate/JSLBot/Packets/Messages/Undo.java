package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class Undo extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 75; }
	public final String getName() { return "Undo"; }
	@Sequence(0)
	public Undo_bAgentData bagentdata=new Undo_bAgentData();
	@Sequence(1)
	public List<Undo_bObjectData> bobjectdata;
}
