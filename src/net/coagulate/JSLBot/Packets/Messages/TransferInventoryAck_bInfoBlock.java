package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class TransferInventoryAck_bInfoBlock extends Block {
	@Sequence(0)
	public LLUUID vtransactionid=new LLUUID();
	@Sequence(1)
	public LLUUID vinventoryid=new LLUUID();
}
