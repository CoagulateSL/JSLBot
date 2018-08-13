package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class CreateInventoryItem extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 305; }
	public final String getName() { return "CreateInventoryItem"; }
	@Sequence(0)
	public CreateInventoryItem_bAgentData bagentdata=new CreateInventoryItem_bAgentData();
	@Sequence(1)
	public CreateInventoryItem_bInventoryBlock binventoryblock=new CreateInventoryItem_bInventoryBlock();
	public CreateInventoryItem(){}
	public CreateInventoryItem(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
