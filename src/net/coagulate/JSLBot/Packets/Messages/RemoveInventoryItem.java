package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RemoveInventoryItem extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 270; }
	public final String getName() { return "RemoveInventoryItem"; }
	@Sequence(0)
	public RemoveInventoryItem_bAgentData bagentdata=new RemoveInventoryItem_bAgentData();
	@Sequence(1)
	public List<RemoveInventoryItem_bInventoryData> binventorydata;
	public RemoveInventoryItem(){}
	public RemoveInventoryItem(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
