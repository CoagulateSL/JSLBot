package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RezObjectFromNotecard_bNotecardData extends Block {
	@Sequence(0)
	public LLUUID vnotecarditemid=new LLUUID();
	@Sequence(1)
	public LLUUID vobjectid=new LLUUID();
}
