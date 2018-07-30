package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class TransferInventory extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 295; }
	public final String getName() { return "TransferInventory"; }
	@Sequence(0)
	public TransferInventory_bInfoBlock binfoblock=new TransferInventory_bInfoBlock();
	@Sequence(1)
	public List<TransferInventory_bInventoryBlock> binventoryblock;
}
