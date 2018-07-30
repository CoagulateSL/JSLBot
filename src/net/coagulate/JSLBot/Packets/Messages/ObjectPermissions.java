package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectPermissions extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 105; }
	public final String getName() { return "ObjectPermissions"; }
	@Sequence(0)
	public ObjectPermissions_bAgentData bagentdata=new ObjectPermissions_bAgentData();
	@Sequence(1)
	public ObjectPermissions_bHeaderData bheaderdata=new ObjectPermissions_bHeaderData();
	@Sequence(2)
	public List<ObjectPermissions_bObjectData> bobjectdata;
}
