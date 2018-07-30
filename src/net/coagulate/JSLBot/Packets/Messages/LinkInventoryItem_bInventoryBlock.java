package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class LinkInventoryItem_bInventoryBlock extends Block {
	@Sequence(0)
	public U32 vcallbackid=new U32();
	@Sequence(1)
	public LLUUID vfolderid=new LLUUID();
	@Sequence(2)
	public LLUUID vtransactionid=new LLUUID();
	@Sequence(3)
	public LLUUID volditemid=new LLUUID();
	@Sequence(4)
	public S8 vtype=new S8();
	@Sequence(5)
	public S8 vinvtype=new S8();
	@Sequence(6)
	public Variable1 vname=new Variable1();
	@Sequence(7)
	public Variable1 vdescription=new Variable1();
}
