package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RequestTaskInventory extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 289; }
	public final String getName() { return "RequestTaskInventory"; }
	@Sequence(0)
	public RequestTaskInventory_bAgentData bagentdata=new RequestTaskInventory_bAgentData();
	@Sequence(1)
	public RequestTaskInventory_bInventoryData binventorydata=new RequestTaskInventory_bInventoryData();
}
