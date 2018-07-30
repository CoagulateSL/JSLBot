package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class SimStats_bStat extends Block {
	@Sequence(0)
	public U32 vstatid=new U32();
	@Sequence(1)
	public F32 vstatvalue=new F32();
}
