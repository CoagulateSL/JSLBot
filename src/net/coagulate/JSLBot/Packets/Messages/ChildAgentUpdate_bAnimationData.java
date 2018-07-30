package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ChildAgentUpdate_bAnimationData extends Block {
	@Sequence(0)
	public LLUUID vanimation=new LLUUID();
	@Sequence(1)
	public LLUUID vobjectid=new LLUUID();
}
