package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RemoveInventoryObjects extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 284; }
	public final String getName() { return "RemoveInventoryObjects"; }
	@Sequence(0)
	public RemoveInventoryObjects_bAgentData bagentdata=new RemoveInventoryObjects_bAgentData();
	@Sequence(1)
	public List<RemoveInventoryObjects_bFolderData> bfolderdata;
	@Sequence(2)
	public List<RemoveInventoryObjects_bItemData> bitemdata;
}
