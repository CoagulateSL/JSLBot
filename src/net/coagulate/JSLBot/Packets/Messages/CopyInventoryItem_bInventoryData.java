package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class CopyInventoryItem_bInventoryData extends Block {
	@Sequence(0)
	public U32 vcallbackid=new U32();
	@Sequence(1)
	public LLUUID voldagentid=new LLUUID();
	@Sequence(2)
	public LLUUID volditemid=new LLUUID();
	@Sequence(3)
	public LLUUID vnewfolderid=new LLUUID();
	@Sequence(4)
	public Variable1 vnewname=new Variable1();
}
