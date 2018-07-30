package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectMaterial extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 97; }
	public final String getName() { return "ObjectMaterial"; }
	@Sequence(0)
	public ObjectMaterial_bAgentData bagentdata=new ObjectMaterial_bAgentData();
	@Sequence(1)
	public List<ObjectMaterial_bObjectData> bobjectdata;
}
