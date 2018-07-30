package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class BuyObjectInventory extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 103; }
	public final String getName() { return "BuyObjectInventory"; }
	@Sequence(0)
	public BuyObjectInventory_bAgentData bagentdata=new BuyObjectInventory_bAgentData();
	@Sequence(1)
	public BuyObjectInventory_bData bdata=new BuyObjectInventory_bData();
}
