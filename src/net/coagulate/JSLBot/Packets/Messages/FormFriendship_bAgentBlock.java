package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class FormFriendship_bAgentBlock extends Block {
	@Sequence(0)
	public LLUUID vsourceid=new LLUUID();
	@Sequence(1)
	public LLUUID vdestid=new LLUUID();
}
