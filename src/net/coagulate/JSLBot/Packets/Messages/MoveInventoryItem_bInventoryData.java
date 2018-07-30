package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class MoveInventoryItem_bInventoryData extends Block {
	@Sequence(0)
	public LLUUID vitemid=new LLUUID();
	@Sequence(1)
	public LLUUID vfolderid=new LLUUID();
	@Sequence(2)
	public Variable1 vnewname=new Variable1();
}
