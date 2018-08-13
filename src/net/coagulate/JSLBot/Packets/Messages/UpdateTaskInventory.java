package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class UpdateTaskInventory extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 286; }
	public final String getName() { return "UpdateTaskInventory"; }
	@Sequence(0)
	public UpdateTaskInventory_bAgentData bagentdata=new UpdateTaskInventory_bAgentData();
	@Sequence(1)
	public UpdateTaskInventory_bUpdateData bupdatedata=new UpdateTaskInventory_bUpdateData();
	@Sequence(2)
	public UpdateTaskInventory_bInventoryData binventorydata=new UpdateTaskInventory_bInventoryData();
	public UpdateTaskInventory(){}
	public UpdateTaskInventory(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
