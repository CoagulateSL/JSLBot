package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AgentAnimation_bAnimationList extends Block {
	@Sequence(0)
	public LLUUID vanimid=new LLUUID();
	@Sequence(1)
	public BOOL vstartanim=new BOOL();
}
