package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class TransferInventory_bInventoryBlock extends Block {
	@Sequence(0)
	public LLUUID vinventoryid=new LLUUID();
	@Sequence(1)
	public S8 vtype=new S8();
}
