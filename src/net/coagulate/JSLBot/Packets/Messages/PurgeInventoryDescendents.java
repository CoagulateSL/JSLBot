package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class PurgeInventoryDescendents extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 285; }
	public final String getName() { return "PurgeInventoryDescendents"; }
	@Sequence(0)
	public PurgeInventoryDescendents_bAgentData bagentdata=new PurgeInventoryDescendents_bAgentData();
	@Sequence(1)
	public PurgeInventoryDescendents_bInventoryData binventorydata=new PurgeInventoryDescendents_bInventoryData();
}
