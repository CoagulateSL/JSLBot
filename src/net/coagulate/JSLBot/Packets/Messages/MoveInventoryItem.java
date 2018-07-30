package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class MoveInventoryItem extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 268; }
	public final String getName() { return "MoveInventoryItem"; }
	@Sequence(0)
	public MoveInventoryItem_bAgentData bagentdata=new MoveInventoryItem_bAgentData();
	@Sequence(1)
	public List<MoveInventoryItem_bInventoryData> binventorydata;
}
