package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class MapLayerReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 406; }
	public final String getName() { return "MapLayerReply"; }
	@Sequence(0)
	public MapLayerReply_bAgentData bagentdata=new MapLayerReply_bAgentData();
	@Sequence(1)
	public List<MapLayerReply_bLayerData> blayerdata;
}
