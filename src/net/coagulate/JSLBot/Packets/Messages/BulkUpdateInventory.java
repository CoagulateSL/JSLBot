package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class BulkUpdateInventory extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 281; }
	public final String getName() { return "BulkUpdateInventory"; }
	@Sequence(0)
	public BulkUpdateInventory_bAgentData bagentdata=new BulkUpdateInventory_bAgentData();
	@Sequence(1)
	public List<BulkUpdateInventory_bFolderData> bfolderdata;
	@Sequence(2)
	public List<BulkUpdateInventory_bItemData> bitemdata;
}
