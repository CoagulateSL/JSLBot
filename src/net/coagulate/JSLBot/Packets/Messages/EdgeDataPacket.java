package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class EdgeDataPacket extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 24; }
	public final String getName() { return "EdgeDataPacket"; }
	@Sequence(0)
	public EdgeDataPacket_bEdgeData bedgedata=new EdgeDataPacket_bEdgeData();
}
