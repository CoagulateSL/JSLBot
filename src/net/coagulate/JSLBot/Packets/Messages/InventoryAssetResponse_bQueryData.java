package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class InventoryAssetResponse_bQueryData extends Block {
	@Sequence(0)
	public LLUUID vqueryid=new LLUUID();
	@Sequence(1)
	public LLUUID vassetid=new LLUUID();
	@Sequence(2)
	public BOOL visreadable=new BOOL();
}
