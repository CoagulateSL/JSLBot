package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class FetchInventory extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 279; }
	public final String getName() { return "FetchInventory"; }
	@Sequence(0)
	public FetchInventory_bAgentData bagentdata=new FetchInventory_bAgentData();
	@Sequence(1)
	public List<FetchInventory_bInventoryData> binventorydata;
}
