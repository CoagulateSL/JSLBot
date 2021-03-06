package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class SaveAssetIntoInventory extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 272; }
	public final String getName() { return "SaveAssetIntoInventory"; }
	@Sequence(0)
	public SaveAssetIntoInventory_bAgentData bagentdata=new SaveAssetIntoInventory_bAgentData();
	@Sequence(1)
	public SaveAssetIntoInventory_bInventoryData binventorydata=new SaveAssetIntoInventory_bInventoryData();
	public SaveAssetIntoInventory(){}
	public SaveAssetIntoInventory(JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
