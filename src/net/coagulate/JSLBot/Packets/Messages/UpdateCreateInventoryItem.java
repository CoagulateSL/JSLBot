package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class UpdateCreateInventoryItem extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 267; }
	public final String getName() { return "UpdateCreateInventoryItem"; }
	@Sequence(0)
	public UpdateCreateInventoryItem_bAgentData bagentdata=new UpdateCreateInventoryItem_bAgentData();
	@Sequence(1)
	public List<UpdateCreateInventoryItem_bInventoryData> binventorydata;
}
