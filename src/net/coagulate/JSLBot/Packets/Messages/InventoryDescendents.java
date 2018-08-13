package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class InventoryDescendents extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 278; }
	public final String getName() { return "InventoryDescendents"; }
	@Sequence(0)
	public InventoryDescendents_bAgentData bagentdata=new InventoryDescendents_bAgentData();
	@Sequence(1)
	public List<InventoryDescendents_bFolderData> bfolderdata;
	@Sequence(2)
	public List<InventoryDescendents_bItemData> bitemdata;
	public InventoryDescendents(){}
	public InventoryDescendents(JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
