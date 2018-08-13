package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class MoveTaskInventory extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 288; }
	public final String getName() { return "MoveTaskInventory"; }
	@Sequence(0)
	public MoveTaskInventory_bAgentData bagentdata=new MoveTaskInventory_bAgentData();
	@Sequence(1)
	public MoveTaskInventory_bInventoryData binventorydata=new MoveTaskInventory_bInventoryData();
	public MoveTaskInventory(){}
	public MoveTaskInventory(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
