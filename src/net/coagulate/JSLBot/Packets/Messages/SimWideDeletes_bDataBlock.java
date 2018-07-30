package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class SimWideDeletes_bDataBlock extends Block {
	@Sequence(0)
	public LLUUID vtargetid=new LLUUID();
	@Sequence(1)
	public U32 vflags=new U32();
}
