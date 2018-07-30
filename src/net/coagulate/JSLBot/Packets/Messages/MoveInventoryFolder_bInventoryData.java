package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class MoveInventoryFolder_bInventoryData extends Block {
	@Sequence(0)
	public LLUUID vfolderid=new LLUUID();
	@Sequence(1)
	public LLUUID vparentid=new LLUUID();
}
