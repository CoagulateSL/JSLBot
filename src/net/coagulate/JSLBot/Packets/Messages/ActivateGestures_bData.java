package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ActivateGestures_bData extends Block {
	@Sequence(0)
	public LLUUID vitemid=new LLUUID();
	@Sequence(1)
	public LLUUID vassetid=new LLUUID();
	@Sequence(2)
	public U32 vgestureflags=new U32();
}
