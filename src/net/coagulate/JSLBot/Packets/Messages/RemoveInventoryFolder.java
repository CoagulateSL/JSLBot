package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RemoveInventoryFolder extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 276; }
	public final String getName() { return "RemoveInventoryFolder"; }
	@Sequence(0)
	public RemoveInventoryFolder_bAgentData bagentdata=new RemoveInventoryFolder_bAgentData();
	@Sequence(1)
	public List<RemoveInventoryFolder_bFolderData> bfolderdata;
}
