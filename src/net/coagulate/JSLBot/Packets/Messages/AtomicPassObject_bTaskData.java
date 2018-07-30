package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AtomicPassObject_bTaskData extends Block {
	@Sequence(0)
	public LLUUID vtaskid=new LLUUID();
	@Sequence(1)
	public BOOL vattachmentneedssave=new BOOL();
}
