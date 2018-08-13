package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class LinkInventoryItem extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 426; }
	public final String getName() { return "LinkInventoryItem"; }
	@Sequence(0)
	public LinkInventoryItem_bAgentData bagentdata=new LinkInventoryItem_bAgentData();
	@Sequence(1)
	public LinkInventoryItem_bInventoryBlock binventoryblock=new LinkInventoryItem_bInventoryBlock();
	public LinkInventoryItem(){}
	public LinkInventoryItem(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
