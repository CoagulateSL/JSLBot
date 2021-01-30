package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class TransferInventory_bValidationBlock extends Block {
	@Sequence(0)
	public BOOL vneedsvalidation=new BOOL();
	@Sequence(1)
	public U32 vestateid=new U32();
}
