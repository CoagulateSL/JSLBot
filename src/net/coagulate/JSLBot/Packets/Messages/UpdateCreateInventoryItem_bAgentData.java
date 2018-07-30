package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class UpdateCreateInventoryItem_bAgentData extends Block {
	@Sequence(0)
	public LLUUID vagentid=new LLUUID();
	@Sequence(1)
	public BOOL vsimapproved=new BOOL();
	@Sequence(2)
	public LLUUID vtransactionid=new LLUUID();
}
