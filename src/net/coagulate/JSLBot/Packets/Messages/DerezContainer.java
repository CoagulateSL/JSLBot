package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class DerezContainer extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 104; }
	public final String getName() { return "DerezContainer"; }
	@Sequence(0)
	public DerezContainer_bData bdata=new DerezContainer_bData();
}
