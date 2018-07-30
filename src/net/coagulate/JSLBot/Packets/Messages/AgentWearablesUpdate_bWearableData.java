package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AgentWearablesUpdate_bWearableData extends Block {
	@Sequence(0)
	public LLUUID vitemid=new LLUUID();
	@Sequence(1)
	public LLUUID vassetid=new LLUUID();
	@Sequence(2)
	public U8 vwearabletype=new U8();
}
