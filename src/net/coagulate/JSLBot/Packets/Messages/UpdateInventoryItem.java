package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class UpdateInventoryItem extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 266; }
	public final String getName() { return "UpdateInventoryItem"; }
	@Sequence(0)
	public UpdateInventoryItem_bAgentData bagentdata=new UpdateInventoryItem_bAgentData();
	@Sequence(1)
	public List<UpdateInventoryItem_bInventoryData> binventorydata;
}
