package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RezMultipleAttachmentsFromInv_bHeaderData extends Block {
	@Sequence(0)
	public LLUUID vcompoundmsgid=new LLUUID();
	@Sequence(1)
	public U8 vtotalobjects=new U8();
	@Sequence(2)
	public BOOL vfirstdetachall=new BOOL();
}
