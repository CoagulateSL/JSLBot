package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AgentRequestSit_bTargetObject extends Block {
	@Sequence(0)
	public LLUUID vtargetid=new LLUUID();
	@Sequence(1)
	public LLVector3 voffset=new LLVector3();
}
