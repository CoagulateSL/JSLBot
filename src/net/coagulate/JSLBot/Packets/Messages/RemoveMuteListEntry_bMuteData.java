package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RemoveMuteListEntry_bMuteData extends Block {
	@Sequence(0)
	public LLUUID vmuteid=new LLUUID();
	@Sequence(1)
	public Variable1 vmutename=new Variable1();
}
