package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class CopyInventoryItem extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 269; }
	public final String getName() { return "CopyInventoryItem"; }
	@Sequence(0)
	public CopyInventoryItem_bAgentData bagentdata=new CopyInventoryItem_bAgentData();
	@Sequence(1)
	public List<CopyInventoryItem_bInventoryData> binventorydata;
	public CopyInventoryItem(){}
	public CopyInventoryItem(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
