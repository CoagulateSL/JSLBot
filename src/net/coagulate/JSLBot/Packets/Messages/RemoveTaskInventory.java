package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RemoveTaskInventory extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 287; }
	public final String getName() { return "RemoveTaskInventory"; }
	@Sequence(0)
	public RemoveTaskInventory_bAgentData bagentdata=new RemoveTaskInventory_bAgentData();
	@Sequence(1)
	public RemoveTaskInventory_bInventoryData binventorydata=new RemoveTaskInventory_bInventoryData();
	public RemoveTaskInventory(){}
	public RemoveTaskInventory(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
