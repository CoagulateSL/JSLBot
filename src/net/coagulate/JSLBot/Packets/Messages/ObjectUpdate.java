package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectUpdate extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 12; }
	public final String getName() { return "ObjectUpdate"; }
	@Sequence(0)
	public ObjectUpdate_bRegionData bregiondata=new ObjectUpdate_bRegionData();
	@Sequence(1)
	public List<ObjectUpdate_bObjectData> bobjectdata;
}
