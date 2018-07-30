package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class MapBlockReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 409; }
	public final String getName() { return "MapBlockReply"; }
	@Sequence(0)
	public MapBlockReply_bAgentData bagentdata=new MapBlockReply_bAgentData();
	@Sequence(1)
	public List<MapBlockReply_bData> bdata;
}
