package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class TransferInventoryAck extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 296; }
	public final String getName() { return "TransferInventoryAck"; }
	@Sequence(0)
	public TransferInventoryAck_bInfoBlock binfoblock=new TransferInventoryAck_bInfoBlock();
}
