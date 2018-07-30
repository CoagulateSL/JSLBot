package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectUpdateCached extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 14; }
	public final String getName() { return "ObjectUpdateCached"; }
	@Sequence(0)
	public ObjectUpdateCached_bRegionData bregiondata=new ObjectUpdateCached_bRegionData();
	@Sequence(1)
	public List<ObjectUpdateCached_bObjectData> bobjectdata;
}
