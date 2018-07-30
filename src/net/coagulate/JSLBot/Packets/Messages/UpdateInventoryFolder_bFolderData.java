package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class UpdateInventoryFolder_bFolderData extends Block {
	@Sequence(0)
	public LLUUID vfolderid=new LLUUID();
	@Sequence(1)
	public LLUUID vparentid=new LLUUID();
	@Sequence(2)
	public S8 vtype=new S8();
	@Sequence(3)
	public Variable1 vname=new Variable1();
}
