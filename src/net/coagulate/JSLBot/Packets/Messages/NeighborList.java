package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class NeighborList extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 3; }
	public final String getName() { return "NeighborList"; }
	@Sequence(0)
	public NeighborList_bNeighborBlock bneighborblock[]=new NeighborList_bNeighborBlock[4];
}
