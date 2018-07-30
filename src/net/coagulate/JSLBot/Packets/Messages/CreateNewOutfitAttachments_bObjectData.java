package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class CreateNewOutfitAttachments_bObjectData extends Block {
	@Sequence(0)
	public LLUUID volditemid=new LLUUID();
	@Sequence(1)
	public LLUUID voldfolderid=new LLUUID();
}
