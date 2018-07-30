package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class MapBlockRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 407; }
	public final String getName() { return "MapBlockRequest"; }
	@Sequence(0)
	public MapBlockRequest_bAgentData bagentdata=new MapBlockRequest_bAgentData();
	@Sequence(1)
	public MapBlockRequest_bPositionData bpositiondata=new MapBlockRequest_bPositionData();
}
