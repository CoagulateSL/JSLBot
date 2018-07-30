package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ReplyTaskInventory_bInventoryData extends Block {
	@Sequence(0)
	public LLUUID vtaskid=new LLUUID();
	@Sequence(1)
	public S16 vserial=new S16();
	@Sequence(2)
	public Variable1 vfilename=new Variable1();
}
