package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectUpdateCompressed extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 13; }
	public final String getName() { return "ObjectUpdateCompressed"; }
	@Sequence(0)
	public ObjectUpdateCompressed_bRegionData bregiondata=new ObjectUpdateCompressed_bRegionData();
	@Sequence(1)
	public List<ObjectUpdateCompressed_bObjectData> bobjectdata;
}
